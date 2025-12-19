package com.example.demo.repository;

import com.example.demo.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Repository for Vehicle entity
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    // Find all vehicles belonging to a user
    List<Vehicle> findByUserId(Long userId);

    // Used to check duplicate vehicle number (DB constraint)
    Optional<Vehicle> findByVehicleNumber(String vehicleNumber);
}
