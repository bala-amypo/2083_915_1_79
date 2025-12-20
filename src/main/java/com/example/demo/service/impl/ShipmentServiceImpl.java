package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.ShipmentService;

import java.time.LocalDate;
import org.springframework.stereotype.Service;

@Service
public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentRepository repo;
    private final VehicleRepository vehicleRepo;
    private final LocationRepository locationRepo;

    public ShipmentServiceImpl(ShipmentRepository repo, VehicleRepository vehicleRepo, LocationRepository locationRepo) {
        this.repo = repo;
        this.vehicleRepo = vehicleRepo;
        this.locationRepo = locationRepo;
    }

    @Override
    public Shipment createShipment(Long vehicleId, Shipment s) {
        if (s.getScheduledDate().isBefore(LocalDate.now()))
    throw new IllegalArgumentException("Scheduled date in past");

if (s.getWeightKg() > v.getCapacityKg())
    throw new IllegalArgumentException("Weight exceeds capacity");

    }

    @Override
    public Shipment getShipment(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shipment not found"));
    }
}
