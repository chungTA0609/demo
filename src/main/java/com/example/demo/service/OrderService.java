package com.example.demo.service;

import com.example.demo.dto.OrderItemDTO;

import java.util.List;

public interface OrderService {
    String checkOrderStatus(Long orderId);
    Long createOrder(Integer customerId, List<OrderItemDTO> orderItems);
    void addOrderItems(Long orderId, List<OrderItemDTO> orderItems);
    void processOrder(Long orderId);
}