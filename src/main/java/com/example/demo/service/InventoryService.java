package com.example.demo.service;

import com.example.demo.entity.Inventory;

import java.time.LocalDateTime;

public interface InventoryService {
     void addInventory(Long productId, Long locationId, Long quantity);
     void updateInventory(Long inventoryId, Long quantity);
    int checkStockLevels(Long productId);
     void scheduleCycleCount(Long locationId, LocalDateTime scheduledDate);
    void performCycleCount(Long cycleCountId, int actualQuantity);
}
