package com.example.demo.repository;

import com.example.demo.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Repository for Shipment entity
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

    // Find shipments by vehicle id
    List<Shipment> findByVehicleId(Long vehicleId);
}
