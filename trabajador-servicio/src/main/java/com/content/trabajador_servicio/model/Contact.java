package com.content.trabajador_servicio.model;

import jakarta.persistence.*;
import lombok.*;

/**
 *
 * Entidad que representa la información de contacto de un empleado.
 */

@Entity
@Table(name = "Contact")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement en MySQL
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(name = "telephone", nullable = false, length = 9)
    private String telephone;
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id", nullable = false)
    private State state;
}
