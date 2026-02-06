package com.example.Swiggato.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity

public class Raddress {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column
    private String branchCode;

    @Column
    private int pincode;

    @Column
    private String city;

    @JoinColumn(name = "Restaurant_id")
    @ManyToOne
    Restaurant restaurant;

}
