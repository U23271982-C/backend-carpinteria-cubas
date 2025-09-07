package com.content.trabajador_servicio.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "State")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement en MySQL
    private int id;
    @Column(name = "state", nullable = false, length = 100)
    private String name_State;

    @OneToMany(targetEntity = Department.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Department department;
}
