@Entity
@Table(name = "cycle_count")
public class CycleCount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cycleCountId;
    @ManyToOne
    @JoinColumn(name = "locationId")
    private Location location;
    private LocalDateTime scheduledDate;
    private LocalDateTime completedDate;
    private String status;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // Getters and setters
}
