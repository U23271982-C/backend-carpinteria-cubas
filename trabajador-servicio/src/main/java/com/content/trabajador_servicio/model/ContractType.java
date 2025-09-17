package com.content.trabajador_servicio.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 *
 * Entidad que representa un tipo de contrato.
 */

@Entity
@Table(name = "ContractType")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContractType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement en MySQL
    private Integer id;
    @Column(name = "contract_type", nullable = false, length = 100)
    private String contract_Type;
    @Column(name = "description", nullable = false, length = 250)
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id", nullable = false)
    private State state;

    @OneToMany(mappedBy = "contractType", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Contract> contracts;
}
