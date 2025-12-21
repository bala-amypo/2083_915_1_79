package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String vehicleNumber;

    private Double capacityKg;

    private Double fuelEfficiency;

    @ManyToOne
    private User user;
}
