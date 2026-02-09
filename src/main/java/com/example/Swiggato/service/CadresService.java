package com.example.Swiggato.service;

import com.example.Swiggato.dto.request.CadresRequest;
import com.example.Swiggato.dto.response.CadresResponse;
import com.example.Swiggato.exceptions.CustomerNotFound;
import com.example.Swiggato.model.Cadres;
import com.example.Swiggato.model.Customer;
import com.example.Swiggato.repository.CadresRepository;
import com.example.Swiggato.repository.CustomerRepository;
import com.example.Swiggato.utility.transformers.CadresTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class CadresService {

    private final CadresRepository cadresRepository;
    private final CustomerRepository customerRepository;

    public CadresResponse addCadresToCustomer(int customerId, CadresRequest cadresRequest) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()->new CustomerNotFound("Invalid Customer Id"));

        Cadres cadres = CadresTransformer.cadresRequestToCadres(cadresRequest);

        //relationship
        customer.getCadresList().add(cadres);
        cadres.setCustomer(customer);

        //save
        customerRepository.save(customer);

        int size = customer.getCadresList().size();
        return CadresTransformer.cadresToCadresResponse(customer.getCadresList().get(size-1));
    }
}
