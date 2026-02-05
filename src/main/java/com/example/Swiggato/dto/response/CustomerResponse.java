package com.example.Swiggato.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class CustomerResponse {
    private String name;
    private String email;
    private String mobNo;
}
