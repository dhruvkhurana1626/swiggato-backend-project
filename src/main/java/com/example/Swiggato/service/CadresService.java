package com.example.Swiggato.service;

import com.example.Swiggato.dto.request.CadresRequest;
import com.example.Swiggato.dto.response.CadresResponse;
import com.example.Swiggato.exceptions.CustomerNotFound;
import com.example.Swiggato.model.Cadres;
import com.example.Swiggato.model.Customer;
import com.example.Swiggato.repository.CadresRepository;
import com.example.Swiggato.repository.CustomerRepository;
import com.example.Swiggato.utility.Functions.Validation;
import com.example.Swiggato.utility.transformers.CadresTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class CadresService {

    private final CadresRepository cadresRepository;
    private final CustomerRepository customerRepository;
    private final Validation validation;

    public CadresResponse addCadresToCustomer(int customerId,
                                              CadresRequest cadresRequest) {

        // Validate customer existence (throws exception if not found)
        Customer customer = validation.checkIfCustomerExist(customerId);

        // Convert request DTO → Cadres entity
        Cadres cadres = CadresTransformer.cadresRequestToCadres(cadresRequest);

        // Establish bidirectional relationship
        cadres.setCustomer(customer);
        customer.getCadresList().add(cadres);

        // Persist via owning aggregate (Customer)
        // Cascade should handle Cadres persistence
        customerRepository.save(customer);

        // Return response for the newly added Cadres
        return CadresTransformer.cadresToCadresResponse(cadres);
    }

}
