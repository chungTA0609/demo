package com.example.demo.controller.shipment;

import com.example.demo.dto.ShipmentDTO;
import com.example.demo.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/shipments")
public class ShipmentController {
    @Autowired
    private ShipmentService shipmentService;

    @PostMapping
    public ResponseEntity<Long> createShipment(@RequestBody ShipmentDTO shipmentDTO) {
        Long shipmentId = shipmentService.createShipment(shipmentDTO.getOrderId(), shipmentDTO.getCarrier(), shipmentDTO.getTrackingNumber());
        return ResponseEntity.status(HttpStatus.CREATED).body(shipmentId);
    }

    @PutMapping("/{shipmentId}/status")
    public ResponseEntity<Void> updateShipmentStatus(@PathVariable Long shipmentId, @RequestBody String status) {
        shipmentService.updateShipmentStatus(shipmentId, status);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{shipmentId}/track")
    public ResponseEntity<ShipmentDTO> trackShipment(@PathVariable Long shipmentId) {
        ShipmentDTO shipmentDTO = shipmentService.trackShipment(shipmentId);
        return ResponseEntity.ok(shipmentDTO);
    }
}
