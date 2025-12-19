package com.example.demo.repository;

import com.example.demo.entity.Location;
import java.util.*;

public interface LocationRepository {
    Location save(Location location);
    Optional<Location> findById(Long id);
    List<Location> findAll();
}
