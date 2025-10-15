package com.content.customer_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

/**
 * Entidad que representa una provincia.
 */

@Entity
@Table(name = "Province")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Province {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer province_id; // ID interno para la base de datos

    @Column(name = "uuid", nullable = false, unique = true, updatable = false)
    private String uuid; // UUID público para la API

    @Column(name = "province_name", nullable = false, length = 100)
    private String province_name;

    @Column(name = "province_code", length = 10)
    private String province_code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department_id;

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
