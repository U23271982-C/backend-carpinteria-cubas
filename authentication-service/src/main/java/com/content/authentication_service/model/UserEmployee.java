package com.content.authentication_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

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

    @Column(name="uuid", nullable = false, unique = true, updatable = false)
    private UUID uuid;

    @Column(name="full_name", length = 150, nullable = false)
    private String full_name;

    @Column(name="user_employee_name", length = 100)
    private String user_employee_name;

    @Column(name="password", nullable = false, updatable = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_employee_position_id")
    private UserEmployeePosition user_employee_position_id;

    @Column(name="user_employee_phone", length = 20)
    private String user_employee_phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

    @OneToMany(mappedBy = "user_employee_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Session> sesions;

    @OneToMany(mappedBy = "user_employee_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserModuleAccess> user_module_accesses;

}
