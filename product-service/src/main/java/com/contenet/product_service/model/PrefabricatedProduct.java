package com.contenet.product_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entidad que representa un producto prefabricado en el sistema.
 * Cada producto prefabricado está asociado a un producto base y un estado (activo, inactivo, eliminado).
 */

@Entity
@Table(name = "PrefabricatedProduct")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrefabricatedProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement en MySQL
    private Integer prefabricated_product_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_base_id", nullable = false)
    private ProductBase product_base_id;

    @Column(name = "prefabricated_product_name", nullable = false, length = 100)
    private String prefabricated_product_name;
    @Column(name = "cod_sku", nullable = false, length = 12)
    private String cod_sku;
    @Column(name = "path_image", nullable = false, length = 255)
    private String path_image;
    @Column(name = "sale_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal sale_price;
    @Column(name = "available_stock", nullable = false)
    private Integer available_stock;
    @Column(name = "min_stock", nullable = false)
    private Integer min_stock;
    @Column(name = "register_date", nullable = false)
    private LocalDateTime last_production_date;
    @Column(name = "specification", nullable = false, length = 255)
    private String specification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

}
