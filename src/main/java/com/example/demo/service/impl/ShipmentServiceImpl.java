package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.ShipmentService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentRepository repo;
    private final VehicleRepository vehicleRepo;
    private final LocationRepository locationRepo;

    public ShipmentServiceImpl(
            ShipmentRepository repo,
            VehicleRepository vehicleRepo,
            LocationRepository locationRepo
    ) {
        this.repo = repo;
        this.vehicleRepo = vehicleRepo;
        this.locationRepo = locationRepo;
    }

    @Override
    public Shipment createShipment(Long vehicleId, Shipment s) {

        Vehicle v = vehicleRepo.findById(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));

        if (s.getWeightKg() > v.getCapacityKg()) {
            throw new IllegalArgumentException("Weight exceeds capacity");
        }

        if (s.getScheduledDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Scheduled date in past");
        }

        s.setVehicle(v);
        s.setPickupLocation(
                locationRepo.findById(s.getPickupLocation().getId()).orElseThrow()
        );
        s.setDropLocation(
                locationRepo.findById(s.getDropLocation().getId()).orElseThrow()
        );

        return repo.save(s);
    }

    @Override
    public Shipment getShipment(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shipment not found"));
    }
}
