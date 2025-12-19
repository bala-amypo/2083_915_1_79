package com.example.demo.repository;

import com.example.demo.entity.RouteOptimizationResult;
import java.util.Optional;

public interface RouteOptimizationResultRepository {
    RouteOptimizationResult save(RouteOptimizationResult result);
    Optional<RouteOptimizationResult> findById(Long id);
}
