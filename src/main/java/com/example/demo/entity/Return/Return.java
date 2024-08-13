package com.example.demo.entity.Return;

import com.example.demo.entity.Shipment.Shipment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "`return`")
public class Return {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long returnId;

    @ManyToOne
    @JoinColumn(name = "shipment_id", nullable = false)
    private Shipment shipment;

    @Column(nullable = false)
    private LocalDateTime returnDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReturnStatus status;

    @OneToMany(mappedBy = "return", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReturnItem> returnItems;

    @ManyToOne
    @JoinColumn(name = "return_reason_id", nullable = true)
    private ReturnReason returnReason;

    // Getters and Setters
}
