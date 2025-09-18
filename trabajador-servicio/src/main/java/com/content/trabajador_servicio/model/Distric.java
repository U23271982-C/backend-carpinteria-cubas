package com.content.trabajador_servicio.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

/**
 *
 * Entidad que representa un distrito en el departamento de Lambayeque.
 */

@Entity
@Table(name = "Distric")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Distric {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement en MySQL
    private Integer id;
    @Column(name = "distric", nullable = false, length = 100)
    private String name_distric;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id", nullable = false)
    private State state;

    @OneToMany(mappedBy = "distric", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Employee> employees;
}
