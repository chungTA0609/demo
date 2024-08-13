package com.example.demo.entity;


import com.example.demo.entity.Location.Location;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cycle_count")
public class CycleCount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cycleCountId;
    @ManyToOne
    @JoinColumn(name = "locationId")
    private Location location;

    private LocalDateTime scheduledDate;
    private LocalDateTime completedDate;
    private int expectQuantity;
    private int actualQuantity;
    private String status;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // Getters and setters
}
