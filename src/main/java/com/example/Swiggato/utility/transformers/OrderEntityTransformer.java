package com.example.Swiggato.utility.transformers;

import com.example.Swiggato.dto.response.OrderEntityResponse;
import com.example.Swiggato.model.OrderEntity;

public class OrderEntityTransformer {
    
    public static OrderEntityResponse OrderEntityToOrderEntityResponse(OrderEntity orderEntity){
        OrderEntityResponse orderEntityResponse = OrderEntityResponse.builder()
                .orderId(orderEntity.getId())
                .totalCost(orderEntity.getTotalCost())
                .createdAt(orderEntity.getCreatedAt())
                .orderStatus(orderEntity.getOrderStatus())
                .build();

        return orderEntityResponse;
    }
}
