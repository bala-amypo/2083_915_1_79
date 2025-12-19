package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Vehicle;

public interface VehicleService {

    // Add a vehicle for a user
    Vehicle addVehicle(Long userId, Vehicle vehicle);

    // Get all vehicles belonging to a user
    List<Vehicle> getVehiclesByUser(Long userId);

    // Find vehicle by ID (used in shipment creation)
    Vehicle findById(Long id);
}
