package com.example.demo.controller.order;

import com.example.demo.dto.*;
import com.example.demo.entity.Order.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.User;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public Order createOrder(@RequestBody OrderCreationRequestDTO request) {
        // Extract customer and order items from the request
        User customer = request.getCustomer();
        List<OrderItem> orderItems = request.getOrderItems();

        return orderService.createOrder(customer, orderItems);
    }

    @PostMapping("/{orderId}/process")
    public void processOrder(@PathVariable Long orderId) {
        orderService.processOrder(orderId);
    }

    @PostMapping("/{orderId}/payment")
    public void handlePayment(@PathVariable Long orderId, @RequestBody PaymentRequestDTO request) {
        orderService.handlePayment(orderId, request.getAmount(), request.getMethod());
    }

    @PostMapping("/{orderId}/ship")
    public void shipOrder(@PathVariable Long orderId, @RequestBody ShipmentRequestDTO request) {
        orderService.shipOrder(orderId, request.getTrackingNumber(), request.getShipmentItems());
    }

    @PostMapping("/{orderId}/complete")
    public void completeOrder(@PathVariable Long orderId) {
        orderService.completeOrder(orderId);
    }
}

