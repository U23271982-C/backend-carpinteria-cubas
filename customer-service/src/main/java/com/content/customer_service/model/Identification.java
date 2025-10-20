package com.content.customer_service.model;

import com.content.customer_service.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

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
@SuperBuilder
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
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

    @OneToMany(mappedBy = "identification_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Client> clients;

}
