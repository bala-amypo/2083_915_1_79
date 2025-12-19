package com.example.demo.service.impl;

import com.example.demo.entity.Location;
import com.example.demo.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    @Override
    public Location addLocation(Location location) {
        location.setId(1L);
        return location;
    }

    @Override
    public Location getLocationById(Long id) {
        return Location.builder()
                .id(id)
                .name("Chennai")
                .latitude(13.0827)
                .longitude(80.2707)
                .build();
    }

    @Override
    public List<Location> getAllLocations() {
        List<Location> locations = new ArrayList<>();
        locations.add(
                Location.builder()
                        .id(1L)
                        .name("Chennai")
                        .latitude(13.0827)
                        .longitude(80.2707)
                        .build()
        );
        return locations;
    }
}
