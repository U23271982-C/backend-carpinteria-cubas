package com.content.inventario_stock_servicio.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "MovementStockPrefabricated")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class MovementStockPrefabricated {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matter_id", nullable = false)
    private Matter Matter;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "movementtype_id", nullable = false)
    private MovementType MovementType;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "stock_before", nullable = false)
    private Integer stock_before;

    @Column(name = "stock_new", nullable = false)
    private Integer stock_new;

    @Column(name = "user_employee", nullable = false)
    private String user_employee;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="state_id", nullable = false)
    private State state;
}
