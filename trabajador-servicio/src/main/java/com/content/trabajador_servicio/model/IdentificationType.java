package com.content.trabajador_servicio.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

/**
 *
 * Entidad que representa un tipo de identificación en el sistema.
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
    private Integer id;
    @Column(name = "identification_type", nullable = false, length = 100)
    private String identification_type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_type_id", nullable = false)
    private PersonType personType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id", nullable = false)
    private State state;

    @OneToMany(mappedBy = "identificationType", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Identification> identifications  ;
}
