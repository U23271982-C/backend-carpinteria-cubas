package com.content.customer_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

/**
 * Entidad que representa el tipo de persona.
 */

@Entity
@Table(name = "PersonType")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer person_type_id; // ID interno para la base de datos

    @Column(name = "uuid", nullable = false, unique = true, updatable = false)
    private String uuid; // UUID público para la API

    @Column(name = "type_name", nullable = false, length = 50)
    private String type_name;

    @Column(name = "description", length = 255)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

    @PrePersist
    private void generateUuid() {
        if (this.uuid == null) {
            this.uuid = UUID.randomUUID().toString();
        }
    }
}
