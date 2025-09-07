package com.content.trabajador_servicio.model;

import jakarta.persistence.*;
import lombok.*;

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
    private String name_Distric;
    @Column(name = "province_id", nullable = false)
    private Integer id_Province;
    @Column(name = "state_id", nullable = false)
    private Integer id_State;
}
