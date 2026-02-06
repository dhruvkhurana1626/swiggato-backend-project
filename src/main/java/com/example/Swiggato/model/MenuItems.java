package com.example.Swiggato.model;

import com.example.Swiggato.utility.enums.FoodCategory;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class MenuItems {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column
    private double price;

    @Column
    boolean isVeg;

    @Column
    boolean isAvailable;

    @Enumerated(EnumType.STRING)
    private FoodCategory foodCategory;

}
