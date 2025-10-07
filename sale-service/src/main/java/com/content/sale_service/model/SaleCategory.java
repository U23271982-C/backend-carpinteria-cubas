package com.content.sale_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "SaleCategory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleCategory {
    /**
     * Categoria de venta, para clasificar las ventas en prefabricados y personalizados
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Nombre de la categoria de venta
     */

    @Column(name = "sale_category_name", length = 20)
    private String sale_category_name;

    /**
     * Estado de la categoria de venta (activo/inactivo/eliminado)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

    /**
     * Lista de ventas asociadas a esta categoria
     */
    @OneToMany(mappedBy = "sale_category", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Sale> sale;
}
