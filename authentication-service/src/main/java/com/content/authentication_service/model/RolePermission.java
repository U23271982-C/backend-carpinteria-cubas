package com.content.authentication_service.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad que representa la relación entre roles y permisos en el sistema.
 * Un rol puede tener múltiples permisos y un permiso puede ser asignado a múltiples roles.
 * Las relaciones están configuradas para carga perezosa.
 */

@Entity
@Table(name = "RolePermission")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RolePermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rol_permission_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permission_id", nullable = false)
    private Permission permission_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

}
