package com.content.trabajador_servicio.model;

import jakarta.persistence.*;
import lombok.*;

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
    @Column(name = "state_id", nullable = false)
    private Integer id_State;
}
