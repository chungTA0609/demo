@Entity
@Table(name="inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventoryId;
    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "locationId")
    private Location location;
    private int quantity;
    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    // Getters and setters
}
