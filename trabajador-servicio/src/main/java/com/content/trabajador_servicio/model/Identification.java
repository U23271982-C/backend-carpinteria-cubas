package com.content.trabajador_servicio.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Identification")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Identification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement en MySQL
    private Integer id;
    @Column(name = "identification", nullable = false, length = 100)
    private String name_Identification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "identification_type_id", nullable = false)
    private IdentificationType identificationType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id", nullable = false)
    private State state;

    @OneToMany(mappedBy = "identification", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Employee> employees;
}
