package com.content.employee_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.util.UUID;

/**
 *
 * Entidad que representa un tipo de identificación en el sistema.
 * Cada tipo de identificación está asociado a un tipo de persona.
 */

@Entity
@Table(name = "IdentificationType")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IdentificationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement en MySQL
    private Integer identification_type_id;

    /**
     * ID público y único
     */
    @Column(nullable = false, unique = true)
    private UUID uuid;

    @Column(name = "identification_type_name", nullable = false, length = 100)
    private String identification_type_name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_type_id", nullable = false)
    private PersonType person_type_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

    @OneToMany(mappedBy = "identification_type_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Employee> employees ;

}
