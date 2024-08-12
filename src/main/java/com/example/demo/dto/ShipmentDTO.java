package com.example.demo.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentDTO {
    public String carrier;
    public String trackingNumber;
    public String status;
    public LocalDateTime shipmentDate;
    public Long orderId;

    public ShipmentDTO(String carrier, String trackingNumber, String status, LocalDateTime shipmentDate) {
        this.carrier = carrier;
        this.trackingNumber = trackingNumber;
        this.status = status;
        this.shipmentDate = shipmentDate;
    }
}
