package com.content.authentication_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

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

    @Column(name="role_name", length = 50)
    private String role_name;

    @Column(name="role_description", length = 100)
    private String role_description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

    @OneToMany(mappedBy = "role_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserRole> user_role;

    @OneToMany(mappedBy = "role_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<RolePermission> role_permission;

}
