package com.example.Swiggato.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RaddressResponse {
    RestaurantResponse restaurantResponse;
    private String branchCode;
    private String pincode;
    private String city;
}
