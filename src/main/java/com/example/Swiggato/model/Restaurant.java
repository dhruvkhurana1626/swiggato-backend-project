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

    @Column
    boolean isOpen;

    @Enumerated(EnumType.STRING)
    private RestaurantStatus restaurantStatus;

    @CreationTimestamp
    private Date creatingAt;

    @OneToMany(mappedBy = "restaurant")
    List<Raddress> raddressList;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable
    List<MenuItems> menuItemsList;

}
