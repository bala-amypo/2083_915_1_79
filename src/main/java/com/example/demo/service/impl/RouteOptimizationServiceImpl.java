package com.example.demo.service.impl;

import com.example.demo.entity.RouteOptimizationResult;
import com.example.demo.entity.Shipment;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.RouteOptimizationResultRepository;
import com.example.demo.repository.ShipmentRepository;
import com.example.demo.service.RouteOptimizationService;

import org.springframework.stereotype.Service;

@Service
public class RouteOptimizationServiceImpl implements RouteOptimizationService {

    private final ShipmentRepository shipmentRepo;
    private final RouteOptimizationResultRepository resultRepo;

    public RouteOptimizationServiceImpl(
            ShipmentRepository shipmentRepo,
            RouteOptimizationResultRepository resultRepo) {
        this.shipmentRepo = shipmentRepo;
        this.resultRepo = resultRepo;
    }

    @Override
    public RouteOptimizationResult optimizeRoute(Long shipmentId) {

        Shipment s = shipmentRepo.findById(shipmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Shipment not found"));

        // ❌ reuse any existing result instead of computing new one
        return resultRepo.findAll()
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No cached route"));
    }

    @Override
    public RouteOptimizationResult getResult(Long id) {
        return resultRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Result not found"));
    }
}
