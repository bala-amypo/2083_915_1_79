package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class RouteOptimizationResult {
    @Id @GeneratedValue
    private Long id;
    private double optimizedDistanceKm;   // ✅ add
    private double estimatedFuelUsageL;   // ✅ add
    private LocalDateTime generatedAt;    // ✅ add

    @OneToOne private Shipment shipment;

    // getters/setters
    public void setShipment(Shipment shipment) { this.shipment = shipment; }
    public void setOptimizedDistanceKm(double km) { this.optimizedDistanceKm = km; }
    public void setEstimatedFuelUsageL(double fuel) { this.estimatedFuelUsageL = fuel; }
    public void setGeneratedAt(LocalDateTime generatedAt) { this.generatedAt = generatedAt; }
}

