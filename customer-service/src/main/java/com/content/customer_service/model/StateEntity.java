package com.content.customer_service.model;

import com.content.customer_service.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Entidad que representa el estado de una entidad (Activo, Inactivo, etc.).
 */

@Entity
@Table(name = "StateEntity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class StateEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer state_entity_id; // ID interno para la base de datos

    @Column(name = "state_name", nullable = false, length = 50)
    private String state_entity_name;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "is_active", nullable = false)
    private Boolean is_active;
}
