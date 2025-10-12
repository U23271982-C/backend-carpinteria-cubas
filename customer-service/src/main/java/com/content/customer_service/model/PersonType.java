package com.content.customer_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

/**
 * Entidad que representa los tipos de personas.
 * Contiene información sobre el tipo de persona y su estado.
 */

@Entity
@Table(name ="PersonType")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer person_type_id;

    @Column(name = "persona_type_name", nullable = false, length = 100)
    private String persona_type_name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

    @OneToMany(mappedBy = "person_type_id", fetch = FetchType.LAZY)
    private List<IdentificationType> identification_type_id;

}
