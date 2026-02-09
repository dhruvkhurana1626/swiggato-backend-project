package com.example.Swiggato.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class CadresResponse {
    CustomerResponse customerResponse;
    private String houseNo;
    private String area;
    private String city;
    private int pincode;
}
