package com.content.authentication_service.model;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "UserModuleAccess")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserModuleAccess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_module_access_id;

    @Column(name="access_granted_at", nullable = false, updatable = false, unique = true )
    private UUID uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_employee_id", nullable = false)
    private UserEmployee user_employee_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id", nullable = false)
    private Module module_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

    @OneToMany(mappedBy = "user_module_access_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserAccessAction> user_access_actions;
}
