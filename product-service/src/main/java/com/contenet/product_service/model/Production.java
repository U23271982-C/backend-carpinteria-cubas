package com.contenet.product_service.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad que representa una producción en el sistema.
 * Cada producción está asociada a un producto personalizado, un estado de producción y un estado general (activo, inactivo, eliminado).
 */

@Entity
@Table(name = "Production")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Production {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement en MySQL
    private Integer production_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customized_product_id", nullable = false)
    private CustomizedProduct customized_product_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "production_state_id", nullable = false)
    private  ProductionState production_state_id;

    @Column(name = "sale_id", nullable = false)
    private Integer sale_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

}
