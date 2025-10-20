package com.content.customer_service.model;

import com.content.customer_service.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Entidad que representa una dirección.
 * ACTUALIZADA para usar SuperBuilder y herencia UUID
 */

@Entity
@Table(name = "Direction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder // Cambiado de @Builder a @SuperBuilder
public class Direction extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer direction_id; // ID interno para la base de datos

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "direction_type_id", nullable = false)
    private DirectionType direction_type_id;

    @Column(name = "direction_name", length = 50)
    private String direction_name;

    @Column(name = "direction_number", length = 20)
    private String direction_number;

    @Column(name = "reference", length = 100)
    private String reference;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id", nullable = false)
    private District district_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;
}
