package com.contenet.product_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

/**
 * Entidad que representa el estado de una producción en el sistema.
 * Cada estado de producción está asociado a un estado general (activo, inactivo, eliminado).
 */

@Entity
@Table(name = "ProductionState")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductionState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement en MySQL
    private Integer production_state_id;

    @Column(name = "production_state_name", nullable = false, length = 100)
    private String production_state_name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

    @OneToMany(mappedBy = "production_state_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Production> productions;

}
