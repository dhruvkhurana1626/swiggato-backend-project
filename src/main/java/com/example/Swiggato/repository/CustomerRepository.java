package com.example.Swiggato.repository;

import com.example.Swiggato.model.Customer;
import com.example.Swiggato.utility.enums.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Integer>{

    boolean existsByEmail(String email);

    //native query
//    @Query(value = "select * from custmer where gender=:g" , nativeQuery = true)
//    List<Customer> findByGender(String g);

//    //jpql query
//    @Query(value = "select c from Customer c where c.gender=:gender")
    List<Customer> findByGender(Gender gender);

}
