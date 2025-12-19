package com.example.demo.controller;

import com.example.demo.entity.Location;
import com.example.demo.service.LocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Handles location-related APIs
@RestController
@RequestMapping("/locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    // Create a location
    @PostMapping
    public Location createLocation(@RequestBody Location location) {
        return locationService.createLocation(location);
    }

    // Get all locations
    @GetMapping
    public List<Location> getAllLocations() {
        return locationService.getAllLocations();
    }

    // Get location by id
    @GetMapping("/{id}")
    public Location getLocationById(@PathVariable Long id) {
        return locationService.findById(id);
    }
}
