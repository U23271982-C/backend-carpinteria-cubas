package com.content.inventory_matter_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "MovementStockCustomized")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovementStockCustomized {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matter_id", nullable = false)
    private Matter matter;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "movement_type_id", nullable = false)
    private MovementType movement_type;

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
    private StateEntity state_entity;
}
