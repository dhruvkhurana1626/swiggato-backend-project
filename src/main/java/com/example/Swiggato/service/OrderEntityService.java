package com.example.Swiggato.service;

import com.example.Swiggato.dto.request.OrderItemsRequest;
import com.example.Swiggato.dto.response.OrderEntityResponse;
import com.example.Swiggato.model.Customer;
import com.example.Swiggato.model.MenuItems;
import com.example.Swiggato.model.OrderEntity;
import com.example.Swiggato.model.OrderItems;
import com.example.Swiggato.repository.MenuItemsRepository;
import com.example.Swiggato.repository.OrderEntityRepository;
import com.example.Swiggato.repository.OrderItemsRepository;
import com.example.Swiggato.utility.Functions.Email;
import com.example.Swiggato.utility.Functions.Validation;
import com.example.Swiggato.utility.enums.OrderStatus;
import com.example.Swiggato.utility.transformers.OrderEntityTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class OrderEntityService {

    private final OrderEntityRepository orderEntityRepository;
    private final OrderItemsRepository orderItemsRepository;
    private final MenuItemsRepository menuItemsRepository;
    private final Validation validation;
    private final Email email;

    @Transactional
    public OrderEntityResponse placeOrder(int customerId,
                                          List<OrderItemsRequest> orderItemsRequestList) {

        // Validate customer existence
        Customer customer = validation.checkIfCustomerExist(customerId);

        // Defensive check: order must contain at least one item
        if (orderItemsRequestList == null || orderItemsRequestList.isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one item");
        }

        // Create order aggregate
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCustomer(customer);
        orderEntity.setOrderStatus(OrderStatus.ORDERPLACED);

        List<OrderItems> orderItemsList = new ArrayList<>();
        double totalCost = 0.0;

        for (OrderItemsRequest request : orderItemsRequestList) {

            // Fetch menu item safely
            MenuItems menuItem = menuItemsRepository.findById(request.getMenuItemId())
                    .orElseThrow(() ->
                            new IllegalArgumentException("Menu item not found: " + request.getMenuItemId())
                    );

            double pricePerUnit = menuItem.getPrice();
            int quantity = request.getQuantity();

            double itemTotal = pricePerUnit * quantity;
            totalCost += itemTotal;

            // Build order item
            OrderItems orderItem = new OrderItems();
            orderItem.setMenuItems(menuItem);
            orderItem.setQuantity(quantity);
            orderItem.setPricePerUnit(pricePerUnit);
            orderItem.setTotalPrice(itemTotal);

            // Maintain bidirectional relationship
            orderItem.setOrderEntity(orderEntity);
            orderItemsList.add(orderItem);
        }

        // Finalize order
        orderEntity.setTotalCost(totalCost);
        orderEntity.setOrderItemsList(orderItemsList);

        // Persist order (cascade should save order items)
        OrderEntity savedOrder = orderEntityRepository.save(orderEntity);

        // Send Email After Order Confirmation
        email.sendEmailForOrderConfirmation(savedOrder);

        // Convert entity → response DTO
        return OrderEntityTransformer.OrderEntityToOrderEntityResponse(savedOrder);
    }

}
