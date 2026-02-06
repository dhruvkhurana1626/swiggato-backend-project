package com.example.Swiggato.repository;

import com.example.Swiggato.model.MenuItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemsRepository extends JpaRepository<MenuItems,Integer> {
}
