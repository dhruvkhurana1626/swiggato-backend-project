package com.example.Swiggato.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder

public class Cadres {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column
    private String houseNo;

    @Column
    private String area;

    @Column
    private String city;

    @Column
    private int pincode;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    Customer customer;

}
