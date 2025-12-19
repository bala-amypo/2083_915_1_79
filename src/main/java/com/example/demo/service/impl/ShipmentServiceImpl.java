package com.example.demo.service.impl;

import com.example.demo.entity.Shipment;
import com.example.demo.service.ShipmentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShipmentServiceImpl implements ShipmentService {

    @Override
    public Shipment createShipment(Shipment shipment) {
        shipment.setId(1L);
        return shipment;
    }

    @Override
    public Shipment getShipmentById(Long id) {
        return Shipment.builder()
                .id(id)
                .description("Electronics")
                .status("CREATED")
                .build();
    }

    @Override
    public List<Shipment> getAllShipments() {
        List<Shipment> shipments = new ArrayList<>();
        shipments.add(
                Shipment.builder()
                        .id(1L)
                        .description("Electronics")
                        .status("CREATED")
                        .build()
        );
        return shipments;
    }
}
