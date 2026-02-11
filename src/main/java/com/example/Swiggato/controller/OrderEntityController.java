package com.example.Swiggato.controller;

import com.example.Swiggato.dto.request.OrderItemsRequest;
import com.example.Swiggato.dto.response.OrderEntityResponse;
import com.example.Swiggato.exceptions.CustomerNotFound;
import com.example.Swiggato.service.OrderEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/orderEntity")
@RequiredArgsConstructor
public class OrderEntityController {

    private final OrderEntityService orderEntityService;

    @PostMapping
    public ResponseEntity placeOrder(@RequestParam int customerId,
                                     @RequestBody List<OrderItemsRequest> orderItemsRequestList){
        try {
            OrderEntityResponse orderEntityResponse = orderEntityService.placeOrder(customerId, orderItemsRequestList);
            return new ResponseEntity(orderEntityResponse, HttpStatus.OK);
        }
        catch (CustomerNotFound e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

}
