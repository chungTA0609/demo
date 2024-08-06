@Entity
@Table(name = "`return`")
public class Return {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long returnId;
    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;
    private LocalDateTime returnDate;
    private String status;
    private String reason;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // Getters and setters
}
