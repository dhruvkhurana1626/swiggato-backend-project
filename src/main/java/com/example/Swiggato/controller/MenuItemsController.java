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

    @PostMapping
    public ResponseEntity addMenuItem(@RequestBody MenuItemsRequest menuItemsRequest,
                                      @RequestParam int restaurantid){
        try{
            MenuItemsResponse menuItemsResponse = menuItemsService.addMenuItem(restaurantid,menuItemsRequest);
            return new ResponseEntity(menuItemsResponse, HttpStatus.OK);
        }
        catch (RestaurantNotFound e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        catch (RestaurantNotActive e){
            return new ResponseEntity(e.getMessage(),HttpStatus.CONFLICT);
        }
    }
}
