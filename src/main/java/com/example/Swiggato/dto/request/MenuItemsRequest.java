package com.example.Swiggato.dto.request;

import com.example.Swiggato.utility.enums.FoodCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class MenuItemsRequest {
    private String name;
    private double price;
    private boolean isVeg;
    private FoodCategory foodCategory;
}
