package com.example.Swiggato.controller;

import com.example.Swiggato.dto.request.CadresRequest;
import com.example.Swiggato.dto.request.CustomerRequest;
import com.example.Swiggato.dto.response.CadresResponse;
import com.example.Swiggato.dto.response.CustomerResponse;
import com.example.Swiggato.exceptions.CustomerNotFound;
import com.example.Swiggato.exceptions.EmailAlreadyUsed;
import com.example.Swiggato.exceptions.PhoneAlreadyUsed;
import com.example.Swiggato.service.CustomerService;
import com.example.Swiggato.utility.enums.Gender;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    /**
     * Create a new customer
     */
    @PostMapping
    public ResponseEntity addCustomer(
            @RequestBody CustomerRequest customerRequest) {

        try {
            CustomerResponse customerResponse =
                    customerService.addCustomer(customerRequest);
            return new ResponseEntity(customerResponse,HttpStatus.CREATED);
        }

        catch (RuntimeException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Fetch customers by gender
     */
    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getCustomersByGender(
            @RequestParam Gender gender) {

        List<CustomerResponse> customers =
                customerService.getCustomerByGender(gender);

        return ResponseEntity.ok(customers);
    }

}
