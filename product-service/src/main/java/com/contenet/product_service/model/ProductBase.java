package com.contenet.product_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Entidad que representa un producto base en el sistema.
 * Cada producto base está asociado a una categoría de producto y un estado (activo, inactivo, eliminado).
 */

@Entity
@Table(name = "ProductBase")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement en MySQL
    private Integer product_base_id;

    @Column(name = "product_base_name", nullable = false, length = 100)
    private String product_base_name;
    @Column(name = "description", nullable = false, length = 100)
    private String description;
    @Column(name = "price_base", nullable = false, precision = 10, scale = 2)
    private BigDecimal price_base;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_category_id", nullable = false)
    private ProductCategory product_category_id;

    @Column(name = "register_date", nullable = false)
    private LocalDateTime register_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

    @OneToMany(mappedBy = "product_base_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PrefabricatedProduct> prefabricated_products;
    @OneToMany(mappedBy = "product_base_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CustomizedProduct> customized_products;

}
