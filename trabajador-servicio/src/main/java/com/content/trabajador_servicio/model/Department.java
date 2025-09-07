package com.content.trabajador_servicio.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Department")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement en MySQL
    private Integer id;
    @Column(name = "department", nullable = false, length = 100)
    private String nameDepartment;
    @Column(name = "state_id", nullable = false)
    private Integer id_State;
}
