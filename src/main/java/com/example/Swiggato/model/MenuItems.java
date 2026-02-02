package com.example.Swiggato.model;

import com.example.Swiggato.utility.enums.FoodCategory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity

public class MenuItems {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column
    private int price;

    @Column
    boolean isVeg;

    @Enumerated(EnumType.STRING)
    private FoodCategory foodCategory;



}
