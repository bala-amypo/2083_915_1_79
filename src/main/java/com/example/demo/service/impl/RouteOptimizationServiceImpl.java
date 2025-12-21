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

    private final ShipmentRepository shipmentRepository;
    private final RouteOptimizationResultRepository resultRepository;

    public RouteOptimizationServiceImpl(ShipmentRepository shipmentRepository,
                                        RouteOptimizationResultRepository resultRepository) {
        this.shipmentRepository = shipmentRepository;
        this.resultRepository = resultRepository;
    }

    @Override
    public RouteOptimizationResult optimizeRoute(Long shipmentId) {

        Shipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Shipment not found"));

        if (shipment.getVehicle() == null ||
                shipment.getVehicle().getFuelEfficiency() == null ||
                shipment.getVehicle().getFuelEfficiency() <= 0) {
            throw new IllegalArgumentException("Invalid vehicle data");
        }

        double distance = Math.hypot(
                shipment.getPickupLocation().getLatitude() -
                        shipment.getDropLocation().getLatitude(),
                shipment.getPickupLocation().getLongitude() -
                        shipment.getDropLocation().getLongitude()
        );

        RouteOptimizationResult result = RouteOptimizationResult.builder()
                .shipment(shipment)
                .optimizedDistanceKm(distance)
                .estimatedFuelUsageL(
                        distance / shipment.getVehicle().getFuelEfficiency()
                )
                .generatedAt(LocalDateTime.now())
                .build();

        return resultRepository.save(result);
    }

    @Override
    public RouteOptimizationResult getResult(Long id) {
        return resultRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Result not found"));
    }
}
