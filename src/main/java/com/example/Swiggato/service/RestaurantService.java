package com.example.Swiggato.service;

import com.example.Swiggato.dto.request.RestaurantRequest;
import com.example.Swiggato.dto.response.RestaurantResponse;
import com.example.Swiggato.model.Restaurant;
import com.example.Swiggato.repository.RestaurantRepository;
import com.example.Swiggato.utility.Functions.Email;
import com.example.Swiggato.utility.Functions.Validation;
import com.example.Swiggato.utility.transformers.RestaurantTransformer;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final Validation validation;
    private final Email email;

    @Transactional
    public RestaurantResponse addRestaurant(RestaurantRequest restaurantRequest) {

        // Validate incoming request (null checks, mandatory fields, etc.)
        validation.validateNewRestaurant(restaurantRequest);

        // Convert request DTO → Restaurant entity
        Restaurant restaurant =
                RestaurantTransformer.restaurantRequestToRestaurant(restaurantRequest);

        // Persist restaurant
        Restaurant savedRestaurant =
                restaurantRepository.save(restaurant);

        //Sending Mail To Restaurant Owner
        email.sendMailAfterRestaurantRegistration(restaurant);

        // Convert saved entity → response DTO
        return RestaurantTransformer.restaurantToRestaurantResponse(savedRestaurant);
    }

}
