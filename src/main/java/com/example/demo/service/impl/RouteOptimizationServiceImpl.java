package com.example.demo.service.impl;

import com.example.demo.service.RouteOptimizationService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RouteOptimizationServiceImpl implements RouteOptimizationService {

    @Override
    public List<String> optimizeRoute(Long shipmentId) {
        // Dummy route steps
        return Arrays.asList(
                "Warehouse",
                "Hub",
                "Destination"
        );
    }
}
