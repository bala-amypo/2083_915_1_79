package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.ShipmentService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentRepository shipmentRepo;
    private final VehicleRepository vehicleRepo;
    private final LocationRepository locationRepo;

    public ShipmentServiceImpl(ShipmentRepository shipmentRepo,
                               VehicleRepository vehicleRepo,
                               LocationRepository locationRepo) {
        this.shipmentRepo = shipmentRepo;
        this.vehicleRepo = vehicleRepo;
        this.locationRepo = locationRepo;
    }

    @Override
    public Shipment createShipment(Long vehicleId, Shipment shipment) {

        Vehicle vehicle = vehicleRepo.findById(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));

        // ✅ weight must be positive
        if (shipment.getWeightKg() <= 0) {
            throw new IllegalArgumentException("Invalid weight");
        }

        // ✅ weight must not exceed capacity
        if (shipment.getWeightKg() > vehicle.getCapacityKg()) {
            throw new IllegalArgumentException("Weight exceeds vehicle capacity");
        }

        // ✅ scheduled date validation
        if (shipment.getScheduledDate() == null ||
                shipment.getScheduledDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Invalid shipment date");
        }

        // ✅ pickup & drop locations must exist
        Location pickup = locationRepo.findById(
                shipment.getPickupLocation().getId()
        ).orElseThrow(() -> new ResourceNotFoundException("Pickup location not found"));

        Location drop = locationRepo.findById(
                shipment.getDropLocation().getId()
        ).orElseThrow(() -> new ResourceNotFoundException("Drop location not found"));

        shipment.setVehicle(vehicle);
        shipment.setPickupLocation(pickup);
        shipment.setDropLocation(drop);

        return shipmentRepo.save(shipment);
    }

    @Override
    public Shipment getShipment(Long shipmentId) {
        return shipmentRepo.findById(shipmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Shipment not found"));
    }
}
