package com.example.demo.repository;

import com.example.demo.entity.CycleCount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CycleCountRepository extends JpaRepository<CycleCount, Long> {
}
