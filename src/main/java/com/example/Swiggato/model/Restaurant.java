package com.example.Swiggato.model;

import com.example.Swiggato.utility.enums.RestaurantStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Restaurant {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(unique = true)
    private String name;

    @Column(unique = true)
    private String email;

    @Column
    boolean isOpen;

    @Enumerated(EnumType.STRING)
    private RestaurantStatus restaurantStatus;

    @CreationTimestamp
    private Date creatingAt;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name="Restaurant_Id")
    List<Raddress> raddressList;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable
    List<MenuItems> menuItemsList;

}
