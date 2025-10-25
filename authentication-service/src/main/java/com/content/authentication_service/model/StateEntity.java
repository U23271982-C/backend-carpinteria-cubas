package com.content.authentication_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "StateEntity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stateId;

    @Column(name = "uuid", nullable = false, unique = true, updatable = false)
    private UUID uuid;

    @Column(name = "state_entity_name", nullable = false, length = 100)
    private String state_entity_name;

    @OneToMany(mappedBy = "state_entity_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserClient> user_clients;

    @OneToMany(mappedBy = "state_entity_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserEmployee> user_employees;

    @OneToMany(mappedBy = "state_entity_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserEmployeePosition> user_employee_positions;

    @OneToMany(mappedBy = "state_entity_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Module> modules;

    @OneToMany(mappedBy = "state_entity_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Action> actions;
}
