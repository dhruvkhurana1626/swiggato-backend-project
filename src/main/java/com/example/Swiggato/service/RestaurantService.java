package com.example.Swiggato.service;

import com.example.Swiggato.dto.request.RestaurantRequest;
import com.example.Swiggato.dto.response.RestaurantResponse;
import com.example.Swiggato.model.Restaurant;
import com.example.Swiggato.repository.RestaurantRepository;
import com.example.Swiggato.utility.transformers.RestaurantTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantResponse addRestaurant(RestaurantRequest restaurantRequest) {
        Restaurant savedRestaurant = restaurantRepository.save(RestaurantTransformer.restaurantRequestToRestaurant(restaurantRequest));
        return RestaurantTransformer.restaurantToRestaurantResponse(savedRestaurant);
    }
}
