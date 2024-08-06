@Entity
@Table(name = "audit_log")
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auditLogId;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    private String action;
    @CreationTimestamp
    private LocalDateTime timestamp;

    // Getters and setters
}
