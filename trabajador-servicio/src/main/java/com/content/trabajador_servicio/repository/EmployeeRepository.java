package com.content.trabajador_servicio.repository;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeRepository {

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

    @Column(name = "position_id", nullable = false)
    private Integer idPosition;

    @Column(name = "identification_id", nullable = false)
    private Integer idIdentification;

    @Column(name = "contract_id", nullable = false)
    private Integer idContract;

    @Column(name = "state", nullable = false)
    private Integer idState;
}

