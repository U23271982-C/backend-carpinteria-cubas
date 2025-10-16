package com.content.employee_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.util.UUID;

/**
 *
 * Entidad que representa un tipo de contrato.
 * Las relaciones están configuradas para carga perezosa y cascada en todas las operaciones.
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
    private Integer contract_type_id;

    /**
     * ID público y único de cada tipo de trabajador.
     */
    @Column(nullable = false, unique = true)
    private UUID uuid;

    @Column(name = "contract_type_name", nullable = false, length = 100)
    private String contract_type_name;
    @Column(name = "description", nullable = false, length = 250)
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

    @OneToMany(mappedBy = "contract_type_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Contract> contracts;

}
