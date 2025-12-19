package com.example.demo.repository;

import com.example.demo.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository for Location entity
public interface LocationRepository extends JpaRepository<Location, Long> {
    // No extra methods needed for now
}
