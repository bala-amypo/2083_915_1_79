package com.example.demo.service;

import com.example.demo.entity.Vehicle;

import java.util.List;

// Service interface for Vehicle related operations
public interface VehicleService {

    // Add a vehicle for a user
    Vehicle addVehicle(Long userId, Vehicle vehicle);

    // Get all vehicles of a user
    List<Vehicle> getVehiclesByUser(Long userId);

    // Find vehicle by id
    Vehicle findById(Long id);
}
