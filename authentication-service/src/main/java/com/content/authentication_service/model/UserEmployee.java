package com.content.authentication_service.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad que representa a los empleados de usuario en el sistema.
 * Incluye detalles como área y puesto.
 * Las relaciones están configuradas para carga perezosa y cascada en todas las operaciones.
 */

@Entity
@Table(name = "UserEmployee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_employee_id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user_id;

    @Column(name = "area", nullable = false,length = 100)
    private String area;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_user_employee_id", nullable = false)
    private PostUserEmployee post_user_employee_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

}
