package com.example.Swiggato.controller;

import com.example.Swiggato.dto.request.RaddressRequest;
import com.example.Swiggato.dto.response.RaddressResponse;
import com.example.Swiggato.exceptions.RestaurantNotFound;
import com.example.Swiggato.service.RaddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/raddress")
@RequiredArgsConstructor
public class RaddressController {

    private final RaddressService raddressService;

    @PostMapping("/restaurants/{restaurantId}/address")
    public ResponseEntity addRestaurantAddress(
            @PathVariable int restaurantId,
            @RequestBody RaddressRequest raddressRequest) {

        try {
            RaddressResponse response =
                    raddressService.addRestaurantAddress(restaurantId, raddressRequest);
            return new ResponseEntity(response,HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

}
