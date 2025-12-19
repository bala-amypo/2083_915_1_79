package com.example.demo.service.impl;

import com.example.demo.entity.Vehicle;
import com.example.demo.service.VehicleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Override
    public Vehicle addVehicle(Vehicle vehicle) {
        vehicle.setId(1L);
        return vehicle;
    }

    @Override
    public Vehicle getVehicleById(Long id) {
        return Vehicle.builder()
                .id(id)
                .vehicleNumber("TN01AB1234")
                .type("Truck")
                .build();
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(
                Vehicle.builder()
                        .id(1L)
                        .vehicleNumber("TN01AB1234")
                        .type("Truck")
                        .build()
        );
        return vehicles;
    }
}
