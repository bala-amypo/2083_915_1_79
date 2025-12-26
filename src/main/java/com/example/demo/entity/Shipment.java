package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double weight;

    // ✅ THIS IS WHAT TESTS EXPECT
    private LocalDate scheduledDate;

    @ManyToOne
    private Vehicle vehicle;

    @ManyToOne
    private Location pickupLocation;

    @ManyToOne
    private Location dropLocation;
}
