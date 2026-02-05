package com.example.Swiggato.service;

import com.example.Swiggato.dto.request.CustomerRequest;
import com.example.Swiggato.dto.response.CustomerResponse;
import com.example.Swiggato.exceptions.EmailAlreadyUsed;
import com.example.Swiggato.model.Customer;
import com.example.Swiggato.repository.CustomerRepository;
import com.example.Swiggato.utility.transformers.CustomerTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerResponse addCustomer(CustomerRequest customerRequest) {
        if(customerRepository.existsByEmail(customerRequest.getEmail())) {
            throw new EmailAlreadyUsed("Email Already Used");
        }
        Customer savedCustomer = customerRepository.save(CustomerTransformer.cutsomerRequestToCustomer(customerRequest));
        return CustomerTransformer.customerToCustomerResponse(savedCustomer);
    }
}
