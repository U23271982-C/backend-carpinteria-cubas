package com.content.trabajador_servicio.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Position")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement en MySQL
    private Integer id;
    @Column(name = "position_name", nullable = false, length = 100)
    private String name_Position;
    @Column(name = "description", nullable = false, length = 250)
    private String description;
    @Column(name = "state_id", nullable = false)
    private Integer id_State;
}
