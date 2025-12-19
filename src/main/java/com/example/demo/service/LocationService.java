package com.example.demo.service;

import com.example.demo.entity.Location;
import java.util.List;

public interface LocationService {

    Location addLocation(Location location);

    Location getLocationById(Long id);

    List<Location> getAllLocations();
}
