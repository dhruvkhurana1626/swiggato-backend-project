package com.example.Swiggato.utility.transformers;

import com.example.Swiggato.dto.request.MenuItemsRequest;
import com.example.Swiggato.dto.response.MenuItemsResponse;
import com.example.Swiggato.model.MenuItems;

public class MenuItemsTransformer {
    public static MenuItems menuItemsRequestToMenuItems(MenuItemsRequest menuItemsRequest){
        MenuItems menuItems = MenuItems.builder()
                .name(menuItemsRequest.getName())
                .price(menuItemsRequest.getPrice())
                .isVeg(menuItemsRequest.isVeg())
                .foodCategory(menuItemsRequest.getFoodCategory())
                .isAvailable(true)
                .build();

        return menuItems;
    }

    public static MenuItemsResponse menuItemToMenuItemsResponse(MenuItems menuItems){
        MenuItemsResponse menuItemsResponse = MenuItemsResponse.builder()
                .name(menuItems.getName())
                .price(menuItems.getPrice())
                .build();

        return menuItemsResponse;
    }
}
