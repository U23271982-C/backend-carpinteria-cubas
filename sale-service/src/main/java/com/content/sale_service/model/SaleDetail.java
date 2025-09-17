package com.content.sale_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "SaleDetail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_id", nullable = false)
    private Sale sale;

    // ide del producto que se vende

    @Column(name = "price_unit")
    private double price_unit;
    @Column(name = "subtotal")
    private double subtotal;
    @Column(name = "total")
    private double total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity;
}
