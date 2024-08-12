package com.example.demo.dto;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDTO {
    public Long productId;
    public Long locationId;
    public Long quantity;
}
