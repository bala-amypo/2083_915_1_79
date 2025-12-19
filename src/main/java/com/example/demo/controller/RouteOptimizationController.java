package com.example.demo.controller;

import com.example.demo.entity.RouteOptimizationResult;
import com.example.demo.service.RouteOptimizationService;
import org.springframework.web.bind.annotation.*;

// Handles route optimization APIs
@RestController
@RequestMapping("/optimize")
public class RouteOptimizationController {

    private final RouteOptimizationService routeOptimizationService;

    public RouteOptimizationController(RouteOptimizationService routeOptimizationService) {
        this.routeOptimizationService = routeOptimizationService;
    }

    // Generate optimized route
    @PostMapping("/{shipmentId}")
    public RouteOptimizationResult optimizeRoute(@PathVariable Long shipmentId) {
        return routeOptimizationService.optimizeRoute(shipmentId);
    }

    // Get optimization result by id
    @GetMapping("/result/{resultId}")
    public RouteOptimizationResult getResult(@PathVariable Long resultId) {
        return routeOptimizationService.getResult(resultId);
    }
}
