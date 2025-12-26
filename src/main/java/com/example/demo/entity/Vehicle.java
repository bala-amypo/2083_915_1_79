package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Vehicle {
    @Id @GeneratedValue
    private Long id;
    private String number;
    private Double capacityKg;
    private Double fuelEfficiency;

    @ManyToOne
    private User user;

    // getters/setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }

    public Double getCapacityKg() { return capacityKg; }
    public void setCapacityKg(Double capacityKg) { this.capacityKg = capacityKg; }

    public Double getFuelEfficiency() { return fuelEfficiency; }
    public void setFuelEfficiency(Double fuelEfficiency) { this.fuelEfficiency = fuelEfficiency; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
