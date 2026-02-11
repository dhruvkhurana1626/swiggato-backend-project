package com.example.Swiggato.service;

import com.example.Swiggato.dto.request.MenuItemsRequest;
import com.example.Swiggato.dto.response.MenuItemsResponse;
import com.example.Swiggato.exceptions.RestaurantNotActive;
import com.example.Swiggato.exceptions.RestaurantNotFound;
import com.example.Swiggato.model.MenuItems;
import com.example.Swiggato.model.Restaurant;
import com.example.Swiggato.repository.MenuItemsRepository;
import com.example.Swiggato.repository.RestaurantRepository;
import com.example.Swiggato.utility.Functions.Validation;
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
    private final Validation validation;

    public MenuItemsResponse addMenuItem(int restaurantId,
                                         MenuItemsRequest menuItemsRequest) {

        // Validate restaurant existence
        Restaurant restaurant =
                validation.checkIfRestaurtantExist(restaurantId);

        // Ensure restaurant is allowed to accept menu updates
        validation.checkIfRestaurantIsActive(restaurant.getRestaurantStatus());

        // Convert request DTO → MenuItem entity
        MenuItems menuItem =
                MenuItemsTransformer.menuItemsRequestToMenuItems(menuItemsRequest);

        // Establish relationship (owning side handled by Restaurant)
        restaurant.getMenuItemsList().add(menuItem);

        // Persist via aggregate root
        restaurantRepository.save(restaurant);

        // Return response for the newly added menu item
        return MenuItemsTransformer.menuItemToMenuItemsResponse(menuItem);
    }

}
