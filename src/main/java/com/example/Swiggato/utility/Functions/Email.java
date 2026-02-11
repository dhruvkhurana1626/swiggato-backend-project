package com.example.Swiggato.utility.Functions;

import com.example.Swiggato.dto.response.CustomerResponse;
import com.example.Swiggato.dto.response.OrderEntityResponse;
import com.example.Swiggato.model.OrderEntity;
import com.example.Swiggato.model.OrderItems;
import com.example.Swiggato.model.Restaurant;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Email {

    private final JavaMailSender javaMailSender;
    public void sendEmailForOrderConfirmation(OrderEntity orderEntity) {

        // Defensive validation
        if (orderEntity == null || orderEntity.getCustomer() == null) {
            throw new IllegalArgumentException("Invalid order data for email");
        }

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        // Sender (should ideally come from configuration)
        mailMessage.setFrom("dhruvjavadev162@gmail.com");

        // Receiver
        mailMessage.setTo(orderEntity.getCustomer().getEmail());

        // Subject
        mailMessage.setSubject("Order Confirmed | Order #" + orderEntity.getId());

        // Build items summary
        StringBuilder itemsSummary = new StringBuilder();
        for (OrderItems orderItems : orderEntity.getOrderItemsList()) {
            itemsSummary.append("• Item ID      : ").append(orderItems.getId()).append("\n")
                    .append("  Quantity     : ").append(orderItems.getQuantity()).append("\n")
                    .append("  Price / Unit : ₹").append(orderItems.getPricePerUnit()).append("\n")
                    .append("  Item Total   : ₹").append(orderItems.getTotalPrice()).append("\n\n");
        }

        // Email body with proper spacing and alignment
        String emailBody =
                "Hi " + orderEntity.getCustomer().getName() + ",\n\n" +

                        "Thank you for placing your order on Swiggato 🎉\n" +
                        "Your order has been successfully confirmed and is now being processed.\n\n" +

                        "====================\n" +
                        "ORDER SUMMARY\n" +
                        "====================\n" +
                        "Order ID     : " + orderEntity.getId() + "\n" +
                        "Order Status : " + orderEntity.getOrderStatus() + "\n" +
                        "Order Date   : " + orderEntity.getCreatedAt() + "\n\n" +

                        "--------------------\n" +
                        "ITEMS ORDERED\n" +
                        "--------------------\n" +
                        itemsSummary +

                        "--------------------\n" +
                        "Total Amount : ₹" + orderEntity.getTotalCost() + "\n" +
                        "--------------------\n\n" +

                        "We’ll notify you as your order progresses.\n" +
                        "If you have any questions, feel free to reach out to our support team.\n\n" +

                        "Happy ordering!\n\n" +
                        "Regards,\n" +
                        "Team Swiggato";

        mailMessage.setText(emailBody);

        // Send email
        javaMailSender.send(mailMessage);
    }



    public void sendEmailForCustomerRegistration(CustomerResponse customerResponse) {

        // Basic safety check to avoid sending invalid emails
        if (customerResponse == null || customerResponse.getEmail() == null) {
            throw new IllegalArgumentException("Customer email is required");
        }

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        // Sender email (should ideally come from application properties)
        mailMessage.setFrom("dhruvjavadev162@gmail.com");

        // Recipient email
        mailMessage.setTo(customerResponse.getEmail());

        // Email subject – short, clear, and welcoming
        mailMessage.setSubject("🎉 Welcome to Swiggato!");

        // Email body – clean, friendly, and professional
        String emailBody = """
            Hi %s,

            Welcome to Swiggato! 🍔🚀

            Your account has been successfully created, and you can now
            explore restaurants, place orders, and enjoy fast deliveries.

            Registered Details:
            • Name   : %s
            • Mobile : %s

            If you did not create this account, please contact our support team immediately.

            Happy ordering!
            Team Swiggato
            """.formatted(
                customerResponse.getName(),
                customerResponse.getName(),
                customerResponse.getMobNo()
        );

        mailMessage.setText(emailBody);

        // Send email
        javaMailSender.send(mailMessage);
    }

    public void sendMailAfterRestaurantRegistration(Restaurant restaurant) {

        // Defensive validation
        if (restaurant == null || restaurant.getEmail() == null) {
            throw new IllegalArgumentException("Invalid restaurant data for email");
        }

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        // Sender email (should ideally come from application properties)
        mailMessage.setFrom("dhruvjavadev162@gmail.com");

        // Recipient email
        mailMessage.setTo(restaurant.getEmail());

        // Email subject
        mailMessage.setSubject("🎉 Restaurant Registered Successfully");

        // Email body – clean, professional, and clear
        String emailBody =
                "Hi,\n\n" +

                        "Your restaurant has been successfully registered on Swiggato.\n\n" +

                        "Restaurant Details\n" +
                        "------------------------------\n" +
                        "Restaurant Name   : " + restaurant.getName() + "\n" +
                        "Restaurant ID     : " + restaurant.getId() + "\n" +
                        "Current Status    : " + restaurant.getRestaurantStatus() + "\n" +
                        "Registered On     : " + restaurant.getCreatingAt() + "\n\n" +

                        "⚠️ Action Required\n" +
                        "------------------------------\n" +
                        "Your restaurant is currently in DRAFT status.\n" +
                        "Please add the restaurant address and required details\n" +
                        "to activate your restaurant and start accepting orders.\n\n" +

                        "If you need any help, feel free to contact our support team.\n\n" +

                        "Regards,\n" +
                        "Team Swiggato";

        mailMessage.setText(emailBody);

        // Send email
        javaMailSender.send(mailMessage);
    }

}
