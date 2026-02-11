package com.example.Swiggato.service;

import com.example.Swiggato.dto.request.RaddressRequest;
import com.example.Swiggato.dto.response.RaddressResponse;
import com.example.Swiggato.exceptions.RestaurantNotFound;
import com.example.Swiggato.model.Raddress;
import com.example.Swiggato.model.Restaurant;
import com.example.Swiggato.repository.RaddressRepository;
import com.example.Swiggato.repository.RestaurantRepository;
import com.example.Swiggato.utility.enums.RestaurantStatus;
import com.example.Swiggato.utility.transformers.RaddressTransformer;
import com.example.Swiggato.utility.transformers.RestaurantTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RaddressService {

    private final RaddressRepository raddressRepository;
    private final RestaurantRepository restaurantRepository;

    public RaddressResponse addRestaurantAddress(int restaurantId, RaddressRequest raddressRequest) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(()-> new RestaurantNotFound("Restauant Id is Invalid"));

        Raddress raddress = RaddressTransformer.raddressRequestToRaddress(raddressRequest);

        //relationship set
        restaurant.getRaddressList().add(raddress);
        restaurant.setRestaurantStatus(RestaurantStatus.ACTIVE);

        //save
        restaurantRepository.save(restaurant);

        //getting the last saved address
        int size = restaurant.getRaddressList().size();
        Raddress savedRaddress = restaurant.getRaddressList().get(size-1);

        //raddress to raddress response & restaurant response added
        RaddressResponse raddressResponse = RaddressTransformer.raddressToRaddressResponse(savedRaddress);
        raddressResponse.setRestaurantResponse(RestaurantTransformer.restaurantToRestaurantResponse(restaurant));

        return raddressResponse;
    }
}
