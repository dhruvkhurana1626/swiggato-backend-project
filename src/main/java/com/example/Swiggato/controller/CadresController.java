package com.example.Swiggato.controller;

import com.example.Swiggato.dto.request.CadresRequest;
import com.example.Swiggato.dto.response.CadresResponse;
import com.example.Swiggato.exceptions.CustomerNotFound;
import com.example.Swiggato.service.CadresService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/cadres")
@RequiredArgsConstructor
public class CadresController {

    private final CadresService cadresService;

    @PostMapping
    public ResponseEntity addCadresToCustomer(@RequestParam int customerId,
                                              @RequestBody CadresRequest cadresRequest){
        try {
            CadresResponse cadresResponse = cadresService.addCadresToCustomer(customerId, cadresRequest);
            return new ResponseEntity(cadresResponse, HttpStatus.OK);
        } catch (CustomerNotFound e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }
}
