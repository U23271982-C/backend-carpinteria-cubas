package com.content.employee_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * Entidad que representa un empleado en el sistema.
 * Cada empleado está asociado a un cargo, distrito, contrato y estado.
 */

@Entity
@Table(name = "Employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement en MySQL
    private Integer employee_id;

    @Column(name = "employee_name", nullable = false, length = 100)
    private String employee_name;
    @Column(name = "employee_last_name", nullable = false, length = 100)
    private String employee_last_name;
    @Column(name = "birth_date", nullable = false)
    private LocalDateTime birth_date;
    @Column(name = "register_date", nullable = false)
    private LocalDateTime register_date;
    @Column(name = "identification_number", nullable = false, length = 12, unique = true)
    private String identification_number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "identification_type_id", nullable = false)
    private IdentificationType identification_type_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id", nullable = false)
    private Contract contract_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "distric_id", nullable = false)
    private Distric distric_id;

    @Column(name = "direction_name", nullable = false)
    private String direction_name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

    @OneToMany(mappedBy = "employee_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Contact> contacts;

}
