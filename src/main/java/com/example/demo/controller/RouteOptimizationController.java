package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.RouteOptimizationResult;
import com.example.demo.service.RouteOptimizationService;

@RestController
@RequestMapping("/optimize")
public class RouteOptimizationController {

    private final RouteOptimizationService optimizationService;

    public RouteOptimizationController(
            RouteOptimizationService optimizationService) {
        this.optimizationService = optimizationService;
    }

    @PostMapping("/{shipmentId}")
    public RouteOptimizationResult optimize(
            @PathVariable Long shipmentId) {
        return optimizationService.optimizeRoute(shipmentId);
    }

    @GetMapping("/result/{resultId}")
    public RouteOptimizationResult getResult(
            @PathVariable Long resultId) {
        return optimizationService.getResult(resultId);
    }
}
