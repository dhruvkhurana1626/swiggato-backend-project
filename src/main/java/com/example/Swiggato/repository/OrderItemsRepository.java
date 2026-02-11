package com.example.Swiggato.repository;

import com.example.Swiggato.model.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItems,String> {
}
