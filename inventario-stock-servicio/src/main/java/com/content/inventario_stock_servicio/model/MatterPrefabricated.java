package com.content.inventario_stock_servicio.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "MatterPrefabricated")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatterPrefabricated {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "matter_id", nullable = false,unique = true)
    private Matter Matter;

    @Column(name = "currently_stock", nullable = false)
    private Integer currently_stock;

    @Column(name = "min_stock", nullable = false)
    private Integer min_stock;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="state_id", nullable = false)
    private State state;
}
