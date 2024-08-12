package com.example.demo.service;

import com.example.demo.dto.ShipmentDTO;

public interface ShipmentService{
    ShipmentDTO trackShipment(Long shipmentId);
    void updateShipmentStatus(Long shipmentId, String status);
    Long createShipment(Long orderId, String carrier, String trackingNumber);
}