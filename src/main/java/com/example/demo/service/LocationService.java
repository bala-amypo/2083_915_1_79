package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Location;

public interface LocationService {

    // Create a pickup/drop location
    Location createLocation(Location location);

    // Fetch all locations
    List<Location> getAllLocations();
}
