package com.example.Swiggato.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity

public class OrderItems {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

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
