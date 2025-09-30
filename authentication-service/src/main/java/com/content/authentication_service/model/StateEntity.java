package com.content.authentication_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

/**
 * Entidad que representa el estado de diferentes entidades en el sistema (0: Eliminado; 1: Activo; 2: Inactivo).
 * Las relaciones están configuradas para carga perezosa y cascada en todas las operaciones.
 */

@Entity
@Table(name = "StateEntity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement en MySQL
    private Integer state_entity_id;

    @Column(name = "state_entity_name", nullable = false, length = 100)
    private String state_entity_name;

    @OneToMany(mappedBy = "state_entity_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Permission> permissions;
    @OneToMany(mappedBy = "state_entity_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PostUserEmployee> postUserEmployees ;
    @OneToMany(mappedBy = "state_entity_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Role> roles ;
    @OneToMany(mappedBy = "state_entity_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<RolePermission> rolePermissions ;
    @OneToMany(mappedBy = "state_entity_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Session> sessions ;
    @OneToMany(mappedBy = "state_entity_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<User> users ;
    @OneToMany(mappedBy = "state_entity_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserClient> userClients ;
    @OneToMany(mappedBy = "state_entity_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserEmployee> userEmployees ;
    @OneToMany(mappedBy = "state_entity_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserRole> userRoles ;
    @OneToMany(mappedBy = "state_entity_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserType> userTypes ;

}
