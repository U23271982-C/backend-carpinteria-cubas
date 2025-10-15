package com.content.customer_service.model;

import com.content.customer_service.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Entidad que representa la identificación de un cliente.
 * ACTUALIZADA para usar SuperBuilder y herencia UUID
 */

@Entity
@Table(name = "Identification")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder // Cambiado de @Builder a @SuperBuilder
public class Identification extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer identification_id; // ID interno para la base de datos

    @Column(name = "identification_number", nullable = false, length = 20)
    private String identification_number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "identification_type_id", nullable = false)
    private IdentificationType identification_type_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_type_id", nullable = false)
    private PersonType person_type_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;
}
