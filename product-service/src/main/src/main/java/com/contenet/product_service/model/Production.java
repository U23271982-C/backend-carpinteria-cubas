package com.contenet.product_service.model;

import jakarta.persistence.*;
import jakarta.persistence.criteria.Order;
import lombok.*;

@Entity
@Table(name = "Production")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Production {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

}
