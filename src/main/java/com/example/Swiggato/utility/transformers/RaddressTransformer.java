package com.example.Swiggato.utility.transformers;

import com.example.Swiggato.dto.request.RaddressRequest;
import com.example.Swiggato.dto.response.RaddressResponse;
import com.example.Swiggato.model.Raddress;

public class RaddressTransformer {

    public static Raddress raddressRequestToRaddress(RaddressRequest raddressRequest){
        String brCode = raddressRequest.getCity().substring(0,2).toUpperCase()
                +raddressRequest.getPincode();

        Raddress raddress = Raddress.builder()
                .pincode(raddressRequest.getPincode())
                .city(raddressRequest.getCity())
                .branchCode(brCode)
                .build();

        return raddress;
    }

    public static RaddressResponse raddressToRaddressResponse(Raddress raddress){
        RaddressResponse raddressResponse = RaddressResponse.builder()
                .branchCode(raddress.getBranchCode())
                .pincode(raddress.getPincode())
                .city(raddress.getCity())
                .build();

        return raddressResponse;
    }
}
