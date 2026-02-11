package com.example.Swiggato.exceptions;

public class RestaurantWithSameName extends RuntimeException {
    public RestaurantWithSameName(String message) {
        super(message);
    }
}
