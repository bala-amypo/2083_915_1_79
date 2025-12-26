package com.example.demo.service.impl;

import com.example.demo.entity.Vehicle;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VehicleRepository;
import com.example.demo.service.VehicleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final UserService userService;
    private final VehicleRepository vehicleRepository;

    public VehicleServiceImpl(UserService userService, VehicleRepository vehicleRepository) {
        this.userService = userService;
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Vehicle addVehicle(Long userId, Vehicle vehicle) {
        if (vehicle.getCapacityKg() <= 0) {
            throw new IllegalArgumentException("Vehicle capacity must be positive");
        }
        User user = userService.findById(userId);
        vehicle.setUser(user);
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle findById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));
    }

    @Override
    public List<Vehicle> getVehiclesByUser(Long userId) {
        return vehicleRepository.findByUserId(userId);
    }
}
