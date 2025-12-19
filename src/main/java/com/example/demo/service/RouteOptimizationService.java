package com.example.demo.service;

import com.example.demo.entity.RouteOptimizationResult;
@Service
// Service interface for Route Optimization
public interface RouteOptimizationService {

    // Generate optimized route for a shipment
    RouteOptimizationResult optimizeRoute(Long shipmentId);

    // Get optimization result by id
    RouteOptimizationResult getResult(Long resultId);
}
