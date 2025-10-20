package com.content.customer_service.model;

import com.content.customer_service.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Entidad que representa el tipo de identificación.
 */

@Entity
@Table(name = "IdentificationType")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class IdentificationType extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer identification_type_id; // ID interno para la base de datos

    @Column(name = "identification_type_name", nullable = false, length = 50)
    private String identification_type_name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_type_id", nullable = false)
    private PersonType person_type_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

    @OneToMany(mappedBy = "identification_type_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Identification> identifications;
}
