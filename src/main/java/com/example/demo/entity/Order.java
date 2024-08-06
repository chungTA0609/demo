@Entity
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private String orderNumber;
    @ManyToOne
    @JoinColumn(name = "customerId")
    private User customer;
    private LocalDateTime orderDate;
    private String status;
    private BigDecimal totalAmount;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // Getters and setters
}
