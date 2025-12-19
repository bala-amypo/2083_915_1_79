package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.ShipmentService;

import java.time.LocalDate;

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
    public Shipment createShipment(Long vehicleId, Shipment shipment) {

        Vehicle vehicle = vehicleRepo.findById(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));

        if (shipment.getWeightKg() > vehicle.getCapacityKg())
            throw new IllegalArgumentException("Weight exceeds capacity");

        if (shipment.getScheduledDate().isBefore(LocalDate.now()))
            throw new IllegalArgumentException("Scheduled date is in the past");

        shipment.setVehicle(vehicle);
        shipment.setPickupLocation(
                locationRepo.findById(shipment.getPickupLocation().getId()).orElseThrow());
        shipment.setDropLocation(
                locationRepo.findById(shipment.getDropLocation().getId()).orElseThrow());

        return repo.save(shipment);
    }

    @Override
    public Shipment getShipment(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shipment not found"));
    }
}
