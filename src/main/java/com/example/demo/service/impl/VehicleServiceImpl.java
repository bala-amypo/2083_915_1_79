package com.example.demo.service.impl;

import com.example.demo.entity.Vehicle;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VehicleRepository;
import com.example.demo.service.VehicleService;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository repo;
    private final UserRepository userRepo;

    public VehicleServiceImpl(VehicleRepository repo, UserRepository userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
    }

    @Override
    public Vehicle addVehicle(Long userId, Vehicle vehicle) {

        if (vehicle.getCapacityKg() <= 0)
            throw new IllegalArgumentException("Capacity must be positive");
        /*
        vehicle.setUser(
                userRepo.findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found"))
        );
        */

        return repo.save(vehicle);
    }

    @Override
    public List<Vehicle> getVehiclesByUser(Long userId) {
        return repo.findByUserId(userId);
    }

    @Override
    public Vehicle findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));
    }
}
