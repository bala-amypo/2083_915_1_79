package com.example.demo.service;

import com.example.demo.entity.Shipment;

public interface ShipmentService {

    // Create shipment for a vehicle
    Shipment createShipment(Long vehicleId, Shipment shipment);

    // Fetch shipment by ID
    Shipment getShipment(Long shipmentId);
}
