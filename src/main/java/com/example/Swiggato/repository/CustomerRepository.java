package com.example.Swiggato.repository;

import com.example.Swiggato.model.Customer;
import com.example.Swiggato.utility.enums.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Integer>{

    boolean existsByEmail(String email);
    boolean existsByMobNo(String email);
    List<Customer> findByGender(Gender gender);

}
