package com.example.demo.controller.order;

import com.example.demo.dto.OrderDTO;
import com.example.demo.dto.OrderItemDTO;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Long> createOrder(@RequestBody OrderDTO orderDTO) {
        Long orderId = orderService.createOrder(orderDTO.getCustomerId(), orderDTO.getOrderItems());
        return ResponseEntity.status(HttpStatus.CREATED).body(orderId);
    }

    @PutMapping("/{orderId}/process")
    public ResponseEntity<Void> processOrder(@PathVariable Long orderId) {
        orderService.processOrder(orderId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/{orderId}/items")
    public ResponseEntity<Void> addOrderItems(@PathVariable Long orderId, @RequestBody List<OrderItemDTO> orderItems) {
        orderService.addOrderItems(orderId, orderItems);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{orderId}/status")
    public ResponseEntity<String> checkOrderStatus(@PathVariable Long orderId) {
        String status = orderService.checkOrderStatus(orderId);
        return ResponseEntity.ok(status);
    }
}

