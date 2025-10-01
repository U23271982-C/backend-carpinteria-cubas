package com.content.authentication_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

/**
 * Entidad que representa un permiso en el sistema.
 * Un permiso puede ser asignado a múltiples roles.
 * Las relaciones están configuradas para carga perezosa y cascada en todas las operaciones.
 */

@Entity
@Table(name = "Permission")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer permission_id;

    @Column(name = "permission_name", nullable = false, length = 50)
    private String permission_name;
    @Column(name = "description", nullable = false, length = 100)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

    @OneToMany(mappedBy = "permission_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<RolePermission> role_permissions;

}
