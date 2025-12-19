package com.example.demo.service;

import com.example.demo.entity.Location;

import java.util.List;

// Service interface for Location operations
public interface LocationService {

    // Create a new location
    Location createLocation(Location location);

    // Get all locations
    List<Location> getAllLocations();

    // Find location by id
    Location findById(Long id);
}
