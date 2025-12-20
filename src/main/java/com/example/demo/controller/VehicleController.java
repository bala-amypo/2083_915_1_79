package com.example.demo.controller;

import com.example.demo.entity.Vehicle;
import com.example.demo.service.VehicleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Handles vehicle-related APIs
@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    // Add a vehicle for a user
    @PostMapping("/{userId}")
    public Vehicle addVehicle(@PathVariable Long userId,
                              @RequestBody Vehicle vehicle) {
        return vehicleService.addVehicle(userId, vehicle);
    }

    // Get all vehicles of a user
    //@GetMapping("/user/{userId}")
    //public List<Vehicle> getVehiclesByUser(@PathVariable Long userId) {
    //    return vehicleService.getVehiclesByUser(userId);
    //}

    // Get vehicle by id
    @GetMapping("/{id}")
    public Vehicle getVehicleById(@PathVariable Long id) {
        return vehicleService.findById(id);
    }
}
