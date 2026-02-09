package com.example.Swiggato.utility.transformers;

import com.example.Swiggato.dto.request.CadresRequest;
import com.example.Swiggato.dto.response.CadresResponse;
import com.example.Swiggato.model.Cadres;

public class CadresTransformer {
    public static Cadres cadresRequestToCadres(CadresRequest cadresRequest){
        Cadres caddress = Cadres.builder()
                .houseNo(cadresRequest.getHouseNo())
                .area(cadresRequest.getArea())
                .city(cadresRequest.getCity())
                .pincode(cadresRequest.getPincode())
                .build();

        return caddress;
    }

    public static CadresResponse cadresToCadresResponse(Cadres cadres){
        CadresResponse cadresResponse = CadresResponse.builder()
                .customerResponse(CustomerTransformer.customerToCustomerResponse(cadres.getCustomer()))
                .houseNo(cadres.getHouseNo())
                .area(cadres.getArea())
                .city(cadres.getCity())
                .pincode(cadres.getPincode())
                .build();

        return cadresResponse;
    }
}
