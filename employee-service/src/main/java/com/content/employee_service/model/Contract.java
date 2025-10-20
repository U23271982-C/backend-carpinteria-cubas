package com.content.employee_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 *
 * Entidad que representa un contrato.
 */

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
    private Integer contract_id;

    /**
     * ID público y único de cada tipo de trabajador.
     */
    @Column(nullable = false, unique = true)
    private UUID uuid;

    @Column(name = "contract_name", nullable = false, length = 100)
    private String contract_name;
    @Column(name = "start_date", nullable = false)
    private LocalDateTime start_date;
    @Column(name = "end_date", nullable = false)
    private LocalDateTime end_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_type_id", nullable = false)
    private ContractType contract_type_id;

    @Column(name = "salary", nullable = false)
    private double salary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

    @OneToMany(mappedBy = "contract_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Employee> employees;
}
