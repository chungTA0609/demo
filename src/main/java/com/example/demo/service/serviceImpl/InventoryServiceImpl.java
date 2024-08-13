package com.example.demo.service.serviceImpl;

import com.example.demo.entity.CycleCount;
import com.example.demo.entity.Inventory.Inventory;
import com.example.demo.entity.Location.Location;
import com.example.demo.entity.Product;
import com.example.demo.repository.CycleCountRepository;
import com.example.demo.repository.InventoryRepository;
import com.example.demo.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Component
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;
    private CycleCountRepository cycleCountRepository;
    @Override
    public void addInventory(Long productId, Long locationId, Long quantity) {
        Inventory inventory = new Inventory();
        inventory.setProduct(new Product(productId));
        inventory.setLocation(new Location(locationId));
        inventory.setQuantity(quantity.intValue());
        inventoryRepository.save(inventory);
    }
    @Override
    public void updateInventory(Long inventoryId, Long quantity) {
        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));
        inventory.setQuantity(quantity.intValue());
        inventoryRepository.save(inventory);
    }

    public int checkStockLevels(Long productId) {
        List<Inventory> inventoryList = inventoryRepository.findById((productId.intValue()));
        return inventoryList.stream().mapToInt(Inventory::getQuantity).sum();
    }

    @Override
    public void scheduleCycleCount(Long locationId, LocalDateTime scheduledDate) {
        CycleCount cycleCount = new CycleCount();
        cycleCount.setLocation(new Location(locationId));
        cycleCount.setScheduledDate(scheduledDate);
        cycleCount.setStatus("Scheduled");
        cycleCountRepository.save(cycleCount);
    }

    @Override
    public void performCycleCount(Long cycleCountId, int actualQuantity) {
        CycleCount cycleCount = cycleCountRepository.findById(cycleCountId)
                .orElseThrow(() -> new RuntimeException("Cycle count not found"));
        cycleCount.setCompletedDate(LocalDateTime.now());
        cycleCount.setStatus("Completed");
        cycleCountRepository.save(cycleCount);

        Inventory inventory = inventoryRepository.findById(cycleCount.getCycleCountId())
                .orElseThrow(() -> new RuntimeException("Inventory not found"));
        inventory.setQuantity(actualQuantity);
        inventoryRepository.save(inventory);
    }
}
