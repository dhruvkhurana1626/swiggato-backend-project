package com.example.Swiggato.service;

import com.example.Swiggato.dto.request.MenuItemsRequest;
import com.example.Swiggato.dto.response.MenuItemsResponse;
import com.example.Swiggato.exceptions.RestaurantNotActive;
import com.example.Swiggato.exceptions.RestaurantNotFound;
import com.example.Swiggato.model.MenuItems;
import com.example.Swiggato.model.Restaurant;
import com.example.Swiggato.repository.MenuItemsRepository;
import com.example.Swiggato.repository.RestaurantRepository;
import com.example.Swiggato.utility.enums.RestaurantStatus;
import com.example.Swiggato.utility.transformers.MenuItemsTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuItemsService {

    private final RestaurantRepository restaurantRepository;
    private final MenuItemsRepository menuItemsRepository;

    public MenuItemsResponse addMenuItem(int restaurantid, MenuItemsRequest menuItemsRequest) {
        Restaurant restaurant = restaurantRepository.findById(restaurantid).
                orElseThrow(() -> new RestaurantNotFound("Restaurant with ID " + restaurantid + " is not Available"));

        if(restaurant.getRestaurantStatus()!=RestaurantStatus.ACTIVE){
            throw new RestaurantNotActive("Restaurant not actice till now, Please fix that issue before trying to add a Item");
        }

        MenuItems menuItems = MenuItemsTransformer.menuItemsRequestToMenuItems(menuItemsRequest);
        restaurant.getMenuItemsList().add(menuItems);

        restaurantRepository.save(restaurant);

        int size = restaurant.getMenuItemsList().size()-1;
        return MenuItemsTransformer.menuItemToMenuItemsResponse(restaurant.getMenuItemsList().get(size));
    }
}
