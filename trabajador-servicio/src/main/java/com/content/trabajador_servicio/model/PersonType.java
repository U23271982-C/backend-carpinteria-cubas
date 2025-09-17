package com.content.trabajador_servicio.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

/**
 *
 * Entidad que representa un tipo de trabajador en el sistema.
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
    private Integer id;
    @Column(name = "person_type", nullable = false, length = 100)
    private String person_Type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id", nullable = false)
    private State state;

    @OneToMany(mappedBy = "personType", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<IdentificationType> identificationTypes ;
}
