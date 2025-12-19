package com.example.demo.service.impl;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Location;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.LocationRepository;
import com.example.demo.service.LocationService;

import java.util.List;
@Service
// Implements LocationService
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public Location createLocation(Location location) {

        // Latitude validation
        if (location.getLatitude() == null ||
            location.getLatitude() < -90 || location.getLatitude() > 90) {
            throw new IllegalArgumentException("Invalid latitude value");
        }

        return locationRepository.save(location);
    }

    @Override
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @Override
    public Location findById(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found"));
    }
}
