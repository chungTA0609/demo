package com.example.demo.repository;

import com.example.demo.entity.Inventory.Inventory;
import com.example.demo.entity.Location.Location;
import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findById(Integer id);
    Inventory findByProductAndLocation(Product product, Location location);
}
