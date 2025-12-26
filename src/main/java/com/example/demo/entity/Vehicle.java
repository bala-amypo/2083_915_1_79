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
    @Id @GeneratedValue
    private Long id;
    private String number;
    private double capacityKg;     // ✅ add
    private double fuelEfficiency; // ✅ add

    @ManyToOne
    private User user;

    // getters/setters
    public double getCapacityKg() { return capacityKg; }
    public void setCapacityKg(double capacityKg) { this.capacityKg = capacityKg; }

    public double getFuelEfficiency() { return fuelEfficiency; }
    public void setFuelEfficiency(double fuelEfficiency) { this.fuelEfficiency = fuelEfficiency; }

    public void setUser(User user) { this.user = user; }
}

