package com.content.authentication_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

/**
 * Entidad que representa un rol en el sistema.
 * Un rol puede tener múltiples permisos y puede ser asignado a múltiples usuarios.
 * Las relaciones están configuradas para carga perezosa y cascada en todas las operaciones.
 */

@Entity
@Table(name = "Role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer role_id;

    @Column(name = "role_name", nullable = false, length = 50)
    private String role_name;
    @Column(name = "description", nullable = false, length = 100)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

    @OneToMany(mappedBy = "role_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<RolePermission> role_permissions;
    @OneToMany(mappedBy = "role_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserRole> user_roles;

}
