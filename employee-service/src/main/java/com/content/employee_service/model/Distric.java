package com.content.employee_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

/**
 *
 * Entidad que representa un distrito en el departamento de Lambayeque.
 * Cada distrito está asociado a un estado y puede tener múltiples empleados.
 *
 */

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
    private Integer distric_id;

    @Column(name = "district_name", nullable = false, length = 100)
    private String district_name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

    @OneToMany(mappedBy = "distric_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Employee> employees;
}
