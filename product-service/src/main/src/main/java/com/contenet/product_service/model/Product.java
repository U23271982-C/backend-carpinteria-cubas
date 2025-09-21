package com.contenet.product_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "Product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "productName", nullable = false, length = 150)
    private String productName;
    @Column(name = "description", length = 255)
    private String description;
    @Column(name = "basePrice", nullable = false)
    private BigDecimal basePrice;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProductCategory_id", nullable = false)
    private ProductCategory category;
    @Column(name = "weight", nullable = false)
    private Double weightKg;
    @Column(name = "dimensions", length = 100)
    private String dimensions;
    @Column(name = "creationDate", nullable = false)
    private LocalDateTime creationDate;
    @Column(name = "state", nullable = false)
    private Boolean active;

}
