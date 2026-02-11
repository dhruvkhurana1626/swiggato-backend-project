package com.example.Swiggato.dto.response;

import com.example.Swiggato.utility.enums.OrderStatus;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class OrderEntityResponse {
    private int orderId;
    private double totalCost;
    private Date createdAt;
    private OrderStatus orderStatus;
}
