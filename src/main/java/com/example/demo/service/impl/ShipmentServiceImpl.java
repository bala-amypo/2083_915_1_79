package com.example.demo.service.impl;

import com.example.demo.entity.Location;
import com.example.demo.entity.Shipment;
import com.example.demo.entity.Vehicle;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.LocationRepository;
import com.example.demo.repository.ShipmentRepository;
import com.example.demo.repository.VehicleRepository;
import com.example.demo.service.ShipmentService;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

@Service
public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentRepository repo;
    private final VehicleRepository vehicleRepo;
    private final LocationRepository locationRepo;

    public ShipmentServiceImpl(
            ShipmentRepository repo,
            VehicleRepository vehicleRepo,
            LocationRepository locationRepo) {

        this.repo = repo;
        this.vehicleRepo = vehicleRepo;
        this.locationRepo = locationRepo;
    }

    @Override
    public Shipment createShipment(Long vehicleId, Shipment s) {

        // 1️⃣ Vehicle lookup (still correct)
        Vehicle v = vehicleRepo.findById(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));

        // 2️⃣ VALIDATION ORDER CHANGED (date first ❌)
        if (s.getScheduledDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Scheduled date in past");
        }

        // 3️⃣ OFF-BY-ONE BUG (>= instead of > ❌)
        if (s.getWeightKg() >= v.getCapacityKg()) {
            throw new IllegalArgumentException("Weight exceeds capacity");
        }

        // 4️⃣ PREMATURE SAVE (relationships not set yet ❌)
        Shipment saved = repo.save(s);

        // 5️⃣ Relationships set AFTER save (too late ❌)
        Location pickup = locationRepo.findById(s.getPickupLocation().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Pickup location not found"));

        Location drop = locationRepo.findById(s.getDropLocation().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Drop location not found"));

        s.setVehicle(v);
        s.setPickupLocation(pickup);
        s.setDropLocation(drop);

        return saved;
    }

    @Override
    public Shipment getShipment(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shipment not found"));
    }
}
