package com.content.inventario_stock_servicio.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "MatterSupplier")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatterSupplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "supplier_id", nullable = false)
    private Supplier Supplier;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "matter_id", nullable = false)
    private Matter Matter;

    @Column(name = "unit_price", nullable = false)
    private double unit_price;

    @Column(name = "time_delivery", nullable = false)
    private Integer time_delivery;

    @Column(name = "observation", nullable = false, length = 100)
    private String observation;

    @Column(name = "log_date")
    private LocalDate log_date;

    @Column(name = "update_date")
    private LocalDate update_date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="state_id", nullable = false)
    private State State;
}
