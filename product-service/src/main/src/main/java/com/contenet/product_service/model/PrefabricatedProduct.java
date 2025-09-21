package com.contenet.product_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "PrefabricatedProduct")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PrefabricatedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "skuCode", nullable = false, length = 50)
    private String skuCode;
    @Column(name = "imagePath", length = 255)
    private String imagePath;
    @Column(name = "availableStock", nullable = false)
    private Integer availableStock;
    @Column(name = "minimumStock", nullable = false)
    private Integer minimumStock;
    @Column(name = "warrantyMonths", nullable = false)
    private Integer warrantyMonths;
    @Column(name = "productionCost", nullable = false)
    private BigDecimal productionCost;
    @Column(name = "lastProductionDate")
    private LocalDateTime lastProductionDate;

}
