package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name="route_optimization_result")
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RouteOptimizationResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double optimizedDistanceKm;
    private double estimatedFuelUsageL;

    private LocalDateTime generatedAt;

    @OneToOne
    private Shipment shipment;
}
