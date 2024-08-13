package com.example.demo.controller.shipment;

import com.example.demo.dto.ShipmentRequestDTO;
import com.example.demo.dto.ShipmentStatusUpdateRequestDTO;
import com.example.demo.entity.Shipment.Shipment;
import com.example.demo.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/shipments")
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    @PostMapping
    public Shipment createShipment(@RequestBody ShipmentRequestDTO request) {
        return shipmentService.createShipment(request.getOrder(), request.getCarrier(), request.getShipmentItems());
    }

    @PutMapping("/{shipmentId}/status")
    public void updateShipmentStatus(@PathVariable Long shipmentId, @RequestBody ShipmentStatusUpdateRequestDTO request) {
        shipmentService.updateShipmentStatus(shipmentId, request.getStatus());
    }

    @GetMapping("/{trackingNumber}")
    public Shipment trackShipment(@PathVariable String trackingNumber) {
        return shipmentService.trackShipment(trackingNumber);
    }
}
