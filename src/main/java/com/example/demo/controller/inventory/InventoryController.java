package com.example.demo.controller.inventory;

import com.example.demo.dto.CycleCountDTO;
import com.example.demo.dto.InventoryDTO;
import com.example.demo.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/add-inventory/")
    public ResponseEntity<Void> addInventory(@RequestBody InventoryDTO inventoryDTO) {
        inventoryService.addInventory(inventoryDTO.getProductId(), inventoryDTO.getLocationId(), inventoryDTO.getQuantity());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update/{inventoryId}")
    public ResponseEntity<Void> updateInventory(@PathVariable Long inventoryId, @RequestBody Long quantity) {
        inventoryService.updateInventory(inventoryId, quantity);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/check/stock-levels/{productId}")
    public ResponseEntity<Integer> checkStockLevels(@PathVariable Long productId) {
        int stockLevels = inventoryService.checkStockLevels(productId);
        return ResponseEntity.ok(stockLevels);
    }

    @PostMapping("/cycle-count/schedule")
    public ResponseEntity<Void> scheduleCycleCount(@RequestBody CycleCountDTO cycleCountDTO) {
        inventoryService.scheduleCycleCount(cycleCountDTO.getLocationId(), cycleCountDTO.getScheduledDate());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/cycle-count/perform")
    public ResponseEntity<Void> performCycleCount(@RequestBody CycleCountDTO cycleCountDTO) {
        inventoryService.performCycleCount(cycleCountDTO.getCycleCountId(), cycleCountDTO.getActualQuantity());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}