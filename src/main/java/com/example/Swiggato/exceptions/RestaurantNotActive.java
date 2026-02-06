package com.example.Swiggato.exceptions;

public class RestaurantNotActive extends RuntimeException {
    public RestaurantNotActive(String message) {
        super(message);
    }
}
