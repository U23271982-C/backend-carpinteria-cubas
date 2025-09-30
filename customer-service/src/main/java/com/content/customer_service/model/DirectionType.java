package com.content.customer_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

/**
 * Entidad que representa un tipo de dirección.
 * Contiene información sobre la descripción del tipo de dirección y su estado.
 */

@Entity
@Table(name ="DirectionType")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DirectionType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer direction_type_id;

    @Column(name = "description", nullable = false, length = 100)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

    @OneToMany(mappedBy = "direction_type_id", fetch = FetchType.LAZY)
    private List<Direction> directions;

}
