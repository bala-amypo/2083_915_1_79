/*
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

        if (s.getWeightKg() == null)
            s.setWeightKg(10.0);

        if (v.getCapacityKg() != null && s.getWeightKg() > v.getCapacityKg())
            throw new RuntimeException("Weight exceeds capacity");

        if (s.getScheduledDate() == null)
            s.setScheduledDate(LocalDate.now().plusDays(1));

        s.setVehicle(v);

        s.setPickupLocation(
                locationRepo.findById(s.getPickupLocation().getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Pickup location not found"))
        );

        s.setDropLocation(
                locationRepo.findById(s.getDropLocation().getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Drop location not found"))
        );

        return repo.save(s);
    }

    @Override
    public Shipment getShipment(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shipment not found"));
    }
}
*/
package com.example.demo.service.impl;

import com.example.demo.entity.Location;
import com.example.demo.entity.Shipment;
import com.example.demo.entity.Vehicle;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.LocationRepository;
import com.example.demo.repository.ShipmentRepository;
import com.example.demo.repository.VehicleRepository;
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

        // ✅ Default weight if not provided
        if (s.getWeightKg() == null) {
            s.setWeightKg(10.0);
        }

        // ✅ Validate weight against vehicle capacity
        if (v.getCapacityKg() != null && s.getWeightKg() > v.getCapacityKg()) {
            throw new IllegalArgumentException("Weight exceeds vehicle capacity");
        }

        // ✅ Default scheduled date if not provided
        if (s.getScheduledDate() == null) {
            s.setScheduledDate(LocalDate.now().plusDays(1));
        }

        // ✅ Reject past scheduled dates
        if (s.getScheduledDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Scheduled date cannot be in the past");
        }

        // ✅ Validate pickup and drop locations
        Location pickup = locationRepo.findById(s.getPickupLocation().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Pickup location not found"));
        Location drop = locationRepo.findById(s.getDropLocation().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Drop location not found"));

        s.setVehicle(v);
        s.setPickupLocation(pickup);
        s.setDropLocation(drop);

        return repo.save(s);
    }

    @Override
    public Shipment getShipment(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shipment not found"));
    }
}

