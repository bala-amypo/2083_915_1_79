package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(
    name = "vehicles",
    uniqueConstraints = @UniqueConstraint(columnNames = "vehicleNumber")
)
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, unique = true)
    private String vehicleNumber;

    @Column(nullable = false)
    private Double capacityKg;

    @Column(nullable = false)
    private Double fuelEfficiency;

    // -------- Constructors --------
    public Vehicle() {}

    // -------- Getters & Setters --------
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public String getVehicleNumber() {
        return vehicleNumber;
    }
    
    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
    
    public Double getCapacityKg() {
        return capacityKg;
    }
    
    public void setCapacityKg(Double capacityKg) {
        this.capacityKg = capacityKg;
    }
    
    public Double getFuelEfficiency() {
        return fuelEfficiency;
    }
    
    public void setFuelEfficiency(Double fuelEfficiency) {
        this.fuelEfficiency = fuelEfficiency;
    }
}
