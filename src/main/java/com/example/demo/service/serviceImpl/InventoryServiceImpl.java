package com.example.demo.service.serviceImpl;

import com.example.demo.entity.CycleCount;
import com.example.demo.entity.Inventory;
import com.example.demo.entity.Location;
import com.example.demo.entity.Product;
import com.example.demo.repository.CycleCountRepository;
import com.example.demo.repository.InventoryRepository;
import com.example.demo.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;
    private CycleCountRepository cycleCountRepository;
    public void addInventory(Long productId, Long locationId, int quantity) {
        Inventory inventory = new Inventory();
        inventory.setProduct(new Product(productId));
        inventory.setLocation(new Location(locationId));
        inventory.setQuantity(quantity);
        inventoryRepository.save(inventory);
    }

    public void updateInventory(Long inventoryId, int quantity) {
        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));
        inventory.setQuantity(quantity);
        inventoryRepository.save(inventory);
    }

    public int checkStockLevels(Long productId) {
        List<Inventory> inventoryList = inventoryRepository.findById(productId);
        return inventoryList.stream().mapToInt(Inventory::getQuantity).sum();
    }

    public void scheduleCycleCount(Long locationId, LocalDateTime scheduledDate) {
        CycleCount cycleCount = new CycleCount();
        cycleCount.setLocation(new Location(locationId));
        cycleCount.setScheduledDate(scheduledDate);
        cycleCount.setStatus("Scheduled");
        cycleCountRepository.save(cycleCount);
    }

    public void performCycleCount(Long cycleCountId, int actualQuantity) {
        CycleCount cycleCount = cycleCountRepository.findById(cycleCountId)
                .orElseThrow(() -> new RuntimeException("Cycle count not found"));
        cycleCount.setCompletedDate(LocalDateTime.now());
        cycleCount.setStatus("Completed");
        cycleCountRepository.save(cycleCount);

        Inventory inventory = inventoryRepository.findById(cycleCount.getLocation().getLocationId())
                .orElseThrow(() -> new RuntimeException("Inventory not found"));
        inventory.setQuantity(actualQuantity);
        inventoryRepository.save(inventory);
    }
}
