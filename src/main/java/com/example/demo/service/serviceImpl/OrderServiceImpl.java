package com.example.demo.service.serviceImpl;

import com.example.demo.dto.OrderItemDTO;
import com.example.demo.entity.Inventory.Inventory;
import com.example.demo.entity.Order.Order;
import com.example.demo.entity.Order.OrderStatus;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.Payment.Payment;
import com.example.demo.entity.Payment.PaymentMethod;
import com.example.demo.entity.Payment.PaymentStatus;
import com.example.demo.entity.Shipment.ShipmentItem;
import com.example.demo.entity.User;
import com.example.demo.repository.InventoryRepository;
import com.example.demo.repository.OrderItemRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.OrderService;
import com.example.demo.service.PaymentService;
import com.example.demo.service.ShipmentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Component
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ShipmentService shipmentService;

    @Transactional
    public Order createOrder(User customer, List<OrderItem> orderItems) {
        // Calculate total amount
        BigDecimal totalAmount = orderItems.stream()
                .map(OrderItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Create new order
        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.NEW);
        order.setTotalAmount(totalAmount);

        order = orderRepository.save(order);

        // Save order items
        for (OrderItem item : orderItems) {
            item.setOrder(order);
            orderItemRepository.save(item);
        }

        return order;
    }

    @Override
    public String checkOrderStatus(Long orderId) {
        return "";
    }

    @Override
    public Long createOrder(Integer customerId, List<OrderItemDTO> orderItems) {
        return (long) 0;
    }

    @Override
    public void addOrderItems(Long orderId, List<OrderItemDTO> orderItems) {

    }

    @Transactional
    public void processOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        if (order.getStatus() != OrderStatus.NEW) {
            throw new IllegalStateException("Order is not in a state to be processed");
        }

        // Reserve inventory for each item
        for (OrderItem item : order.getOrderItems()) {
            Inventory inventory = inventoryRepository.findById(item.getProduct().getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("Inventory not found for product"));

            if (inventory.getQuantity() < item.getQuantity()) {
                throw new IllegalArgumentException("Insufficient inventory for product: " + item.getProduct().getName());
            }

            inventory.setQuantity(inventory.getQuantity() - item.getQuantity());
            inventoryRepository.save(inventory);
        }

        // Update order status
        order.setStatus(OrderStatus.PROCESSING);
        orderRepository.save(order);
    }

    @Transactional
    public void handlePayment(Long orderId, BigDecimal amount, PaymentMethod method) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        Payment payment = paymentService.processPayment(order, amount, method);

        order.setPayment(payment);
        orderRepository.save(order);

        if (payment.getStatus() == PaymentStatus.COMPLETED) {
            order.setStatus(OrderStatus.PROCESSING);
            orderRepository.save(order);
        }
    }

    @Transactional
    public void shipOrder(Long orderId, String trackingNumber, List<ShipmentItem> carrier) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        if (order.getStatus() != OrderStatus.PROCESSING) {
            throw new IllegalStateException("Order is not ready for shipment");
        }

        shipmentService.createShipment(order, trackingNumber, carrier);

        order.setStatus(OrderStatus.SHIPPED);
        orderRepository.save(order);
    }

    @Transactional
    public void completeOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        if (order.getStatus() != OrderStatus.SHIPPED) {
            throw new IllegalStateException("Order is not yet shipped");
        }

        order.setStatus(OrderStatus.COMPLETED);
        orderRepository.save(order);
    }
}
