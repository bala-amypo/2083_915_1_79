package com.example.demo.repository;

import com.example.demo.entity.Vehicle;
import java.util.*;

public interface VehicleRepository {
    Vehicle save(Vehicle vehicle);
    Optional<Vehicle> findById(Long id);
    Optional<Vehicle> findByVehicleNumber(String number);
    List<Vehicle> findByUserId(Long userId);
}
