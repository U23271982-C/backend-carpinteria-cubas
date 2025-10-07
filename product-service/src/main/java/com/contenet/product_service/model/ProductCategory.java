package com.contenet.product_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

/**
 * Entidad que representa una categoría de producto en el sistema.
 * Cada categoría de producto está asociada a un estado (activo, inactivo, eliminado).
 * Las relaciones están configuradas para carga perezosa y cascada en todas las operaciones.
 */

@Entity
@Table(name = "ProductCategory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement en MySQL
    private Integer product_category_id;

    @Column(name = "product_category_name", nullable = false, length = 100)
    private String product_category_name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

    @OneToMany(mappedBy = "product_category_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProductBase> productBases;

}
