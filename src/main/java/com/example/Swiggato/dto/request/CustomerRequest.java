package com.example.Swiggato.dto.request;

import com.example.Swiggato.utility.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class CustomerRequest {
    private String name;
    private String email;
    private String mobNo;
    private Gender gender;
}
