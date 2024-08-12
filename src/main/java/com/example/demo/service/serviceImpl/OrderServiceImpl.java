package com.example.demo.service.serviceImpl;

import com.example.demo.dto.OrderItemDTO;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.repository.OrderItemRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public Long createOrder(Integer customerId, List<OrderItemDTO> orderItems) {
        Order order = new Order();
        order.setCustomer(new User(customerId));
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("Pending");
        orderRepository.save(order);

        for (OrderItemDTO item : orderItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(new Product(item.getProductId()));
            orderItem.setQuantity(item.getQuantity());
            orderItem.setUnitPrice(item.getUnitPrice());
            orderItemRepository.save(orderItem);
        }
        return order.getOrderId();
    }

    @Override
    public void processOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        if ("Pending".equals(order.getStatus())) {
            order.setStatus("Processed");
            orderRepository.save(order);
        } else {
            throw new RuntimeException("Order already processed");
        }
    }
    @Override
    public void addOrderItems(Long orderId, List<OrderItemDTO> orderItems) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        for (OrderItemDTO item : orderItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(new Product(item.getProductId()));
            orderItem.setQuantity(item.getQuantity());
            orderItem.setUnitPrice(item.getUnitPrice());
            orderItemRepository.save(orderItem);
        }
    }

    @Override
    public String checkOrderStatus(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return order.getStatus();
    }
}
