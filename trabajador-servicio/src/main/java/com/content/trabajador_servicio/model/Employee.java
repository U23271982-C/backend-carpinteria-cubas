package com.content.trabajador_servicio.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * Entidad que representa un empleado en el sistema.
 */

@Entity
@Table(name = "Employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement en MySQL
    private Integer id;
    @Column(name = "firstname", nullable = false, length = 100)
    private String firstname;
    @Column(name = "lastname", nullable = false, length = 100)
    private String lastname;
    @Column(name = "date_birth", nullable = false)
    private LocalDateTime dateBirth;
    @Column(name = "date_admission", nullable = false)
    private LocalDateTime dateAdmission;
    @Column(name = "address", nullable = false)
    private String address;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "district_id", nullable = false)
    private Distric distric;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "identification_id", nullable = false)
    private Identification identification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id", nullable = false)
    private Contract contract;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id", nullable = false)
    private State state;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Contact> contacts;

}
