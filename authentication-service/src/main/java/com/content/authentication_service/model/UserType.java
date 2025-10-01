package com.content.authentication_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

/**
 * Entidad que representa los diferentes tipos de usuarios en el sistema (e.g., Admin, Cliente, Empleado).
 * Las relaciones están configuradas para carga perezosa y cascada en todas las operaciones.
 */

@Entity
@Table(name = "UserType")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_type_id;

    @Column(name = "user_type_name", nullable = false, length = 50)
    private String user_type_name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

    @OneToMany(mappedBy = "user_type_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<User> users;

}
