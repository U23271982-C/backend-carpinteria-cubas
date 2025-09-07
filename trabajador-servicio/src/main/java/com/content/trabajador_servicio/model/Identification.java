package com.content.trabajador_servicio.model;

import jakarta.persistence.*;
import lombok.*;

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
    @Column(name = "identification_type_id", nullable = false)
    private Integer id_Identification_Type;
    @Column(name = "state_id", nullable = false)
    private Integer id_State;
}
