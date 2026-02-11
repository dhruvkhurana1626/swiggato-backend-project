package com.example.Swiggato.utility.transformers;

import com.example.Swiggato.dto.request.RestaurantRequest;
import com.example.Swiggato.dto.response.RestaurantResponse;
import com.example.Swiggato.model.Restaurant;
import com.example.Swiggato.utility.enums.RestaurantStatus;

import java.util.ArrayList;

public class RestaurantTransformer {

    public static Restaurant restaurantRequestToRestaurant(RestaurantRequest restaurantRequest){
        Restaurant restaurant = Restaurant.builder()
                .name(restaurantRequest.getName())
                .email(restaurantRequest.getEmail())
                .restaurantStatus(RestaurantStatus.DRAFT)
                .raddressList(new ArrayList<>())
                .menuItemsList(new ArrayList<>())
                .build();

        return restaurant;
    }
    public static RestaurantResponse restaurantToRestaurantResponse(Restaurant restaurant){
        RestaurantResponse restaurantResponse = RestaurantResponse.builder()
                .name(restaurant.getName())
                .restaurantStatus(restaurant.getRestaurantStatus())
                .creatingAt(restaurant.getCreatingAt())
                .build();

        return restaurantResponse;
    }
}
