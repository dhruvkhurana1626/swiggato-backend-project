package com.example.Swiggato.controller;

import com.example.Swiggato.dto.request.RestaurantRequest;
import com.example.Swiggato.dto.response.RestaurantResponse;
import com.example.Swiggato.exceptions.EmailAlreadyUsed;
import com.example.Swiggato.exceptions.RestaurantWithSameName;
import com.example.Swiggato.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/restaurant")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity addRestaurant(
            @RequestBody RestaurantRequest restaurantRequest) {

        try {
            RestaurantResponse response =
                    restaurantService.addRestaurant(restaurantRequest);
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
