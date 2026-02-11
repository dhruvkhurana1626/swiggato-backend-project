package com.example.Swiggato.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class OrderItems {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private String id;

    @Column
    private int quantity;

    @Column
    private double pricePerUnit;

    @Column
    private double totalPrice;

    @JoinColumn(name = "orderentity_id")
    @ManyToOne
    OrderEntity orderEntity;

    @ManyToOne
    @JoinColumn(name="menu_item_id")
    MenuItems menuItems;

}
