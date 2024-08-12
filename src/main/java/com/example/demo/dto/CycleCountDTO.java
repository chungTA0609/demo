package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CycleCountDTO {
    public Long locationId;
    public LocalDateTime scheduledDate;
    public Long cycleCountId;
    public int actualQuantity;
}
