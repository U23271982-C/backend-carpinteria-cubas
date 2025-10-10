package com.content.inventory_matter_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
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
    private Integer matter_supplier_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "supplier_id", nullable = false)
    private Supplier supplier;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "matter_id", nullable = false)
    private Matter matter;

    @Column(name = "unit_price", nullable = false)
    private BigDecimal unit_price;

    @Column(name = "delivery_time_days", nullable = false)
    private Integer delivery_time_days;

    @Column(name = "log_date")
    private LocalDate log_date;

    @Column(name = "update_date")
    private LocalDate update_date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="state_entity_id", nullable = false)
    private StateEntity state_entity;
}