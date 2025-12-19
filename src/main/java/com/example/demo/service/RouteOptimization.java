package com.example.demo.service;

import com.example.demo.entity.RouteOptimizationResult;

public interface RouteOptimizationService {

    // Generate optimized route for a shipment
    RouteOptimizationResult optimizeRoute(Long shipmentId);

    // Fetch optimization result
    RouteOptimizationResult getResult(Long resultId);
}
