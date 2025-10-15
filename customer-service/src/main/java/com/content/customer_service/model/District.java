package com.content.customer_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

/**
 * Entidad que representa un distrito.
 */

@Entity
@Table(name = "District")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer district_id; // ID interno para la base de datos

    @Column(name = "uuid", nullable = false, unique = true, updatable = false)
    private String uuid; // UUID público para la API

    @Column(name = "district_name", nullable = false, length = 100)
    private String district_name;

    @Column(name = "district_code", length = 10)
    private String district_code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province_id", nullable = false)
    private Province province_id;

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
