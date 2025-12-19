package com.example.demo.service;

import com.example.demo.entity.Shipment;

// Service interface for Shipment operations
public interface ShipmentService {

    // Create shipment for a vehicle
    Shipment createShipment(Long vehicleId, Shipment shipment);

    // Get shipment by id
    Shipment getShipment(Long shipmentId);
}
