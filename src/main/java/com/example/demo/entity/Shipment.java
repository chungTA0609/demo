@Entity
@Table(name = "shipment")
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shipmentId;
    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;
    private String carrier;
    private String trackingNumber;
    private LocalDateTime shipmentDate;
    private String status;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // Getters and setters
}
