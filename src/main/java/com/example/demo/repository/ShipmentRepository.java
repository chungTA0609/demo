package com.example.demo.repository;

import com.example.demo.entity.Shipment.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    Shipment findByTrackingNumber(String trackingNumber);
}
