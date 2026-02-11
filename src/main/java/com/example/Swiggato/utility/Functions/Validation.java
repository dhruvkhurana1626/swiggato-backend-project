package com.example.Swiggato.utility.Functions;

import com.example.Swiggato.dto.request.CustomerRequest;
import com.example.Swiggato.dto.request.RestaurantRequest;
import com.example.Swiggato.exceptions.*;
import com.example.Swiggato.model.Customer;
import com.example.Swiggato.model.Restaurant;
import com.example.Swiggato.repository.CustomerRepository;
import com.example.Swiggato.repository.RestaurantRepository;
import com.example.Swiggato.utility.enums.RestaurantStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Validation {

    private final CustomerRepository customerRepository;
    private final RestaurantRepository restaurantRepository;

    public Customer checkIfCustomerExist(int customerId){
        return customerRepository.findById(customerId)
                .orElseThrow(()-> new CustomerNotFound("Customer with Id-" + customerId +" dont exist"));
    }

    public Restaurant checkIfRestaurtantExist(int restaurantId){
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(()->new RestaurantNotFound("Restaurant Id is Invalid"));
    }

    public void checkIfRestaurantIsActive(RestaurantStatus restaurantStatus){
        if(restaurantStatus!=RestaurantStatus.ACTIVE)throw new RestaurantNotActive("" +
                "Restaurant not actice till now, Please fix that issue before trying to add a Item");
    }

    public void validateNewRestaurant(RestaurantRequest restaurantRequest){
        if(restaurantRepository.existsByName(restaurantRequest.getName())){
            throw new RestaurantWithSameName("Restaurant Name is Already Used");
        }
        if(restaurantRepository.existsByEmail(restaurantRequest.getEmail())){
            throw new EmailAlreadyUsed("Email Already Used");
        }
    }

    public void validateNewCustomer(CustomerRequest request) {

        // Prevent duplicate customer email
        if (customerRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyUsed("Email already in use");
        }

        // Prevent duplicate customer phone number
        if (customerRepository.existsByMobNo(request.getMobNo())) {
            throw new PhoneAlreadyUsed("Phone number already in use");
        }

    }
}
