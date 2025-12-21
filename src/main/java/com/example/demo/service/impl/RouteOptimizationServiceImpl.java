package com.example.demo.service.impl;

import com.example.demo.entity.RouteOptimizationResult;
import com.example.demo.entity.Shipment;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.RouteOptimizationResultRepository;
import com.example.demo.repository.ShipmentRepository;
import com.example.demo.service.RouteOptimizationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RouteOptimizationServiceImpl implements RouteOptimizationService {

    private final ShipmentRepository shipmentRepo;
    private final RouteOptimizationResultRepository resultRepo;

    public RouteOptimizationServiceImpl(
            ShipmentRepository shipmentRepo,
            RouteOptimizationResultRepository resultRepo
    ) {
        this.shipmentRepo = shipmentRepo;
        this.resultRepo = resultRepo;
    }

    @Override
    public RouteOptimizationResult optimizeRoute(Long shipmentId) {

        Shipment s = shipmentRepo.findById(shipmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Shipment not found"));

        double latDiff = s.getPickupLocation().getLatitude() - s.getDropLocation().getLatitude();
        double lonDiff = s.getPickupLocation().getLongitude() - s.getDropLocation().getLongitude();

        double distance = Math.sqrt(latDiff * latDiff + lonDiff * lonDiff);

        double fuelEfficiency = s.getVehicle().getFuelEfficiency();
        if (fuelEfficiency <= 0) fuelEfficiency = 1.0;

        RouteOptimizationResult result = new RouteOptimizationResult();
        result.setShipment(s);
        result.setOptimizedDistanceKm(distance);
        result.setEstimatedFuelUsageL(distance / fuelEfficiency);
        result.setGeneratedAt(LocalDateTime.now());

        return resultRepo.save(result);
    }

    @Override
    public RouteOptimizationResult getResult(Long id) {
        return resultRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Result not found"));
    }
}
