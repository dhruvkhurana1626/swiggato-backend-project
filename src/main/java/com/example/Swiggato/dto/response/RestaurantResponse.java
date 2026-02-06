package com.example.Swiggato.dto.response;

import com.example.Swiggato.model.MenuItems;
import com.example.Swiggato.utility.enums.RestaurantStatus;
import lombok.*;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class RestaurantResponse {
    private String name;
    private Date creatingAt;
    private RestaurantStatus restaurantStatus;
    private List<MenuItems> menuItems;
}
