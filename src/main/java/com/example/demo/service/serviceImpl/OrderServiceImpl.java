@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    public Long createOrder(Long customerId, List<OrderItemDTO> orderItems) {
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

    public String checkOrderStatus(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return order.getStatus();
    }
}
