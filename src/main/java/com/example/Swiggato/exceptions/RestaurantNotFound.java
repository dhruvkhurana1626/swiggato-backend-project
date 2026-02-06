package com.example.Swiggato.exceptions;

public class RestaurantNotFound extends RuntimeException {
    public RestaurantNotFound(String message) {
        super(message);
    }
}
