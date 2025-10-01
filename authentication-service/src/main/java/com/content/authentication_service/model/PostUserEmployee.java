package com.content.authentication_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

/**
 * Entidad que representa los puestos de los empleados en el sistema.
 * Incluye detalles como el nombre del puesto y su estado.
 * Las relaciones están configuradas para carga perezosa y cascada en todas las operaciones.
 */

@Entity
@Table(name = "PostUserEmployee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostUserEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer post_user_employee_id;

    @Column(name = "post_user_employee_name", nullable = false, length = 100)
    private String post_user_employee_name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

    @OneToMany(mappedBy = "post_user_employee_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserEmployee> user_employees;
}
