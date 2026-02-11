package com.example.Swiggato.repository;

import com.example.Swiggato.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant,Integer> {
    boolean existsByName(String name);
    boolean existsByEmail(String email);
}
