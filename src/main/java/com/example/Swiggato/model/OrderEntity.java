package com.example.Swiggato.model;

import com.example.Swiggato.utility.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity

public class OrderEntity {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private String id;

    private double totalCost;

    @CreationTimestamp
    Date createdAt;

    @Enumerated(EnumType.STRING)
    OrderStatus orderStatus;


}
