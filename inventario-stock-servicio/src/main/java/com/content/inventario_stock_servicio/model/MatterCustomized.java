package com.content.inventario_stock_servicio.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "MatterCustomized")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatterCustomized {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "matter_id", nullable = false, unique = true)
    private Matter matter;   // ✅ field name should be lowercase "matter"

    @Column(name = "currently_stock", nullable = false)
    private Integer currently_stock;

    @Column(name = "min_stock", nullable = false)
    private Integer min_stock;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="state_id", nullable = false)
    private State state;
}
