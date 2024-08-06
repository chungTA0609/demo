package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="inventory")
@RequiredArgsConstructor
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventoryId;
    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "locationId")
    private Location location;
    private int quantity;
    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    public void setProduct(Product product) {
    }

    public void setQuantity(int quantity) {
    }

    public void setLocation(Location location) {
    }

    public int getQuantity() {
    }

    // Getters and setters
}
