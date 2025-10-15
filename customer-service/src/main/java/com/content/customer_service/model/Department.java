package com.content.customer_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

/**
 * Entidad que representa un departamento.
 */

@Entity
@Table(name = "Department")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer department_id; // ID interno para la base de datos

    @Column(name = "uuid", nullable = false, unique = true, updatable = false)
    private String uuid; // UUID público para la API

    @Column(name = "department_name", nullable = false, length = 100)
    private String department_name;

    @Column(name = "department_code", length = 10)
    private String department_code;

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
