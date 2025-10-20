package com.content.employee_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.util.UUID;

/**
 *
 * Entidad que representa un tipo de trabajador en el sistema.
 * Cada tipo de trabajador puede tener múltiples tipos de identificación asociados.
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
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement en MySQL
    private Integer person_type_id;

    @Column(name = "person_type_name", nullable = false, length = 100)
    private String person_type_name;

    /**
     * ID público y único de cada tipo de trabajador.
     */
    @Column(nullable = false, unique = true)
    private UUID uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

    @OneToMany(mappedBy = "person_type_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<IdentificationType> identification_types ;

}
