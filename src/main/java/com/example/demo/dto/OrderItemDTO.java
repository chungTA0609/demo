package com.example.demo.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {
    private Long orderId;
    private Long productId;
    private int quantity;
    private BigDecimal unitPrice;
}
