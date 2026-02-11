package com.example.Swiggato.controller;

import com.example.Swiggato.dto.request.MenuItemsRequest;
import com.example.Swiggato.dto.response.MenuItemsResponse;
import com.example.Swiggato.exceptions.RestaurantNotActive;
import com.example.Swiggato.exceptions.RestaurantNotFound;
import com.example.Swiggato.model.MenuItems;
import com.example.Swiggato.service.MenuItemsService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/menuItems")
@RequiredArgsConstructor
public class MenuItemsController {

    private final MenuItemsService menuItemsService;

    @PostMapping("/restaurants/{restaurantId}/menu-items")
    public ResponseEntity addMenuItem(
            @PathVariable int restaurantId,
            @RequestBody MenuItemsRequest menuItemsRequest) {

        try {
            MenuItemsResponse response =
                    menuItemsService.addMenuItem(restaurantId, menuItemsRequest);
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
