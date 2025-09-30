package com.content.customer_service.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad que representa una dirección.
 * Contiene información sobre la ubicación y referencias a otros tipos relacionados.
 */

@Entity
@Table(name ="Direction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Direction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer direction_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "direction_type_id", nullable = false)
    private DirectionType direction_type_id;

    @Column(name = "direction_name", length = 50)
    private String direction_name;
    @Column(name = "direction_number", length = 50)
    private String direction_number;
    @Column(name = "reference", length = 255)
    private String reference;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id", nullable = false)
    private District district_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

}
