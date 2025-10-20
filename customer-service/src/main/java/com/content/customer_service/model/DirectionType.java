package com.content.customer_service.model;

import com.content.customer_service.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.util.List;

/**
 * Entidad que representa el tipo de dirección.
 */

@Entity
@Table(name = "DirectionType")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class DirectionType extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer direction_type_id; // ID interno para la base de datos

    @Column(name = "direction_type_name", nullable = false, length = 50)
    private String direction_type_name;

    @Column(name = "description", length = 255)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

    @OneToMany(mappedBy = "direction_type_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Direction> directions;
}
