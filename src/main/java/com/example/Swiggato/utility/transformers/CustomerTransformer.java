package com.example.Swiggato.utility.transformers;

import com.example.Swiggato.dto.request.CustomerRequest;
import com.example.Swiggato.dto.response.CustomerResponse;
import com.example.Swiggato.model.Customer;

import java.util.ArrayList;

public class CustomerTransformer {

    public static Customer cutsomerRequestToCustomer(CustomerRequest customerRequest){
        Customer customer = Customer.builder()
                .name(customerRequest.getName())
                .email(customerRequest.getEmail())
                .mobNo(customerRequest.getMobNo())
                .gender(customerRequest.getGender())
                .caddresseslist(new ArrayList<>())
                .orderEntities(new ArrayList<>())
                .build();

        return customer;
    }

    public static CustomerResponse customerToCustomerResponse(Customer customer){
        CustomerResponse customerResponse = CustomerResponse.builder()
                .name(customer.getName())
                .email(customer.getEmail())
                .mobNo(customer.getMobNo())
                .build();

        return customerResponse;
    }
}
