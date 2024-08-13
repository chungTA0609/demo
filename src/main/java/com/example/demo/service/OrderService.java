package com.example.demo.service;

import com.example.demo.dto.OrderItemDTO;
import com.example.demo.entity.Order.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.Payment.PaymentMethod;
import com.example.demo.entity.Shipment.ShipmentItem;
import com.example.demo.entity.User;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    String checkOrderStatus(Long orderId);
    Long createOrder(Integer customerId, List<OrderItemDTO> orderItems);
    Order createOrder(User customer, List<OrderItem> orderItems);
    void addOrderItems(Long orderId, List<OrderItemDTO> orderItems);
    void processOrder(Long orderId);
    void completeOrder(Long orderId);
    void shipOrder(Long orderId, String trackingNumber, List<ShipmentItem> shipmentItems);
    void handlePayment(Long orderId, BigDecimal amount, PaymentMethod method);
}