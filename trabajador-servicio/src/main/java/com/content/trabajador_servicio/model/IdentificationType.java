package com.content.trabajador_servicio.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "IdentificationType")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IdentificationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement en MySQL
    private Integer id;
    @Column(name = "identification_type", nullable = false, length = 100)
    private String identification_type;
    @Column(name = "person_type_id", nullable = false)
    private Integer id_Person_Type;
    @Column(name = "state_id", nullable = false)
    private Integer id_State;
}
