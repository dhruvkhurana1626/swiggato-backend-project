package com.example.Swiggato.repository;

import com.example.Swiggato.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderEntityRepository extends JpaRepository<OrderEntity,Integer> {
}
