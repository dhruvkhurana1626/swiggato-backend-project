package com.example.Swiggato.service;

import com.example.Swiggato.dto.request.CustomerRequest;
import com.example.Swiggato.dto.response.CustomerResponse;
import com.example.Swiggato.model.Customer;
import com.example.Swiggato.repository.CustomerRepository;
import com.example.Swiggato.utility.Functions.Email;
import com.example.Swiggato.utility.Functions.Validation;
import com.example.Swiggato.utility.enums.Gender;
import com.example.Swiggato.utility.transformers.CustomerTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final Validation validation;
    private final Email email;

    public CustomerResponse addCustomer(CustomerRequest customerRequest) {

        // Validate request data (null checks, email uniqueness, mandatory fields)
        // Keeps service logic clean and reusable
        validation.validateNewCustomer(customerRequest);

        // Convert DTO → Entity before persistence
        Customer customer =
                CustomerTransformer.cutsomerRequestToCustomer(customerRequest);

        // Persist customer in database
        Customer savedCustomer = customerRepository.save(customer);

        // Convert saved entity → response DTO
        CustomerResponse customerResponse = CustomerTransformer.customerToCustomerResponse(savedCustomer);

        // Sending the Confirmation Email - Once the Customer is Added
        email.sendEmailForCustomerRegistration(customerResponse);

        // Returing the Customer Response DTO
        return customerResponse;
    }

    public List<CustomerResponse> getCustomerByGender(Gender gender) {

        // Defensive check to avoid invalid queries and NPEs
        if (gender == null) {
            throw new IllegalArgumentException("Gender must not be null");
        }

        // Fetch customers by gender, convert entities to response DTOs
        return customerRepository.findByGender(gender)
                .stream()
                .map(CustomerTransformer::customerToCustomerResponse)
                .toList();
    }



}
