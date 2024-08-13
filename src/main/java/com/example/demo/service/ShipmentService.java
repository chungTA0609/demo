package com.example.demo.service;

import com.example.demo.entity.Order.Order;
import com.example.demo.entity.Shipment.Shipment;
import com.example.demo.entity.Shipment.ShipmentItem;
import com.example.demo.entity.Shipment.ShipmentStatus;

import java.util.List;

public interface ShipmentService{
    void updateShipmentStatus(Long shipmentId, ShipmentStatus status);
    Shipment trackShipment(String trackingNumber);
    Shipment createShipment(Order order, String carrierName, List<ShipmentItem> shipmentItems);
}