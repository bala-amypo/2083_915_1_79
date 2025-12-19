package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

// Represents a vehicle owned by a user
@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    private Long id;

    private String vehicleNumber;
    private Double capacityKg;
    private Double fuelEfficiency;

    // Many vehicles belong to one user
    @ManyToOne
    private User user;

    public Vehicle() {}

    public Vehicle(Long id, String vehicleNumber, Double capacityKg, Double fuelEfficiency, User user) {
        this.id = id;
        this.vehicleNumber = vehicleNumber;
        this.capacityKg = capacityKg;
        this.fuelEfficiency = fuelEfficiency;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public Double getCapacityKg() {
        return capacityKg;
    }

    public Double getFuelEfficiency() {
        return fuelEfficiency;
    }

    public User getUser() {
        return user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public void setCapacityKg(Double capacityKg) {
        this.capacityKg = capacityKg;
    }

    public void setFuelEfficiency(Double fuelEfficiency) {
        this.fuelEfficiency = fuelEfficiency;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
