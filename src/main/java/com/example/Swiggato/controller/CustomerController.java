package com.example.Swiggato.controller;

import com.example.Swiggato.dto.request.CustomerRequest;
import com.example.Swiggato.dto.response.CustomerResponse;
import com.example.Swiggato.exceptions.EmailAlreadyUsed;
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

    @PostMapping
    public ResponseEntity addCustomer(@RequestBody CustomerRequest customerRequest){
        try{
            CustomerResponse customerResponse = customerService.addCustomer(customerRequest);
            return new ResponseEntity(customerResponse, HttpStatus.OK);
        } catch (EmailAlreadyUsed e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping
    public ResponseEntity getCustomerByGender(@RequestParam Gender gender){
        List<CustomerResponse> customerResponseList = customerService.getCustomerByGender(gender);
        return new ResponseEntity(customerResponseList,HttpStatus.OK);
    }

}
