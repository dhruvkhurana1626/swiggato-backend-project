package com.example.Swiggato.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder

public class MenuItemsResponse {
    private String name;
    private double price;
}
