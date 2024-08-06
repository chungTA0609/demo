package com.example.demo.service.serviceImpl;

import com.example.demo.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShipmentServiceImpl implements ShipmentService {
    @Autowired
    private ShipmentRepository shipmentRepository;

    public Long createShipment(Long orderId, String carrier, String trackingNumber) {
        Shipment shipment = new Shipment();
        shipment.setOrder(new Order(orderId));
        shipment.setCarrier(carrier);
        shipment.setTrackingNumber(trackingNumber);
        shipment.setShipmentDate(LocalDateTime.now());
        shipment.setStatus("Pending");
        shipmentRepository.save(shipment);
        return shipment.getShipmentId();
    }

    public void updateShipmentStatus(Long shipmentId, String status) {
        Shipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new RuntimeException("Shipment not found"));
        shipment.setStatus(status);
        if ("Shipped".equals(status)) {
            shipment.setShipmentDate(LocalDateTime.now());
        }
        shipmentRepository.save(shipment);
    }

    public ShipmentDTO trackShipment(Long shipmentId) {
        Shipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new RuntimeException("Shipment not found"));
        return new ShipmentDTO(shipment.getCarrier(), shipment.getTrackingNumber(), shipment.getStatus(), shipment.getShipmentDate());
    }
}
