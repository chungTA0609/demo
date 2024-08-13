package com.example.demo.service.serviceImpl;

import com.example.demo.entity.Carrier;
import com.example.demo.entity.Order.Order;
import com.example.demo.entity.Shipment.Shipment;
import com.example.demo.entity.Shipment.ShipmentItem;
import com.example.demo.entity.Shipment.ShipmentStatus;
import com.example.demo.repository.CarrierRepository;
import com.example.demo.repository.ShipmentRepository;
import com.example.demo.service.ShipmentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Component
@RequiredArgsConstructor
public class ShipmentServiceImpl implements ShipmentService {
    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private CarrierRepository carrierRepository;

    @Transactional
    public Shipment createShipment(Order order, String carrierName, List<ShipmentItem> shipmentItems) {
        Carrier carrier = carrierRepository.findByName(carrierName);

        Shipment shipment = new Shipment();
        shipment.setOrder(order);
        shipment.setCarrier(carrier);
        shipment.setTrackingNumber(generateTrackingNumber());
        shipment.setShipmentDate(LocalDateTime.now());
        shipment.setStatus(ShipmentStatus.PENDING);

        shipment.setShipmentItems(shipmentItems);
        for (ShipmentItem item : shipmentItems) {
            item.setShipment(shipment);
        }

        return shipmentRepository.save(shipment);
    }

    @Transactional
    public void updateShipmentStatus(Long shipmentId, ShipmentStatus status) {
        Shipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new IllegalArgumentException("Shipment not found"));

        shipment.setStatus(status);
        shipmentRepository.save(shipment);
    }

    @Transactional
    public Shipment trackShipment(String trackingNumber) {
        return shipmentRepository.findByTrackingNumber(trackingNumber);
    }

    private String generateTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}
