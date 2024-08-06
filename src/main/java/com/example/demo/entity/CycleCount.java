package com.example.demo.entity;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

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
    private String status;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public void setLocation(Location location) {
    }

    public void setScheduledDate(LocalDateTime scheduledDate) {
    }

    public void setStatus(String scheduled) {
    }

    public void setCompletedDate(LocalDateTime now) {
    }

    public Object getLocation() {
    }

    // Getters and setters
}
