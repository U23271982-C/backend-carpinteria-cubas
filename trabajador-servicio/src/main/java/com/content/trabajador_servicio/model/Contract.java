package com.content.trabajador_servicio.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Contract")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement en MySQL
    private Integer id;
    @Column(name = "contract", nullable = false, length = 100)
    private String name_Contract;
    @Column(name = "start_date", nullable = false)
    private LocalDateTime start_Date;
    @Column(name = "finish_date", nullable = false)
    private LocalDateTime finish_Date;
    @Column(name = "contract_type", nullable = false)
    private Integer contract_Type;
    @Column(name = "salary", nullable = false)
    private double salary;
    @Column(name = "state_id", nullable = false)
    private Integer id_State;
}
