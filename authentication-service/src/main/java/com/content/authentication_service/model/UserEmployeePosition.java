package com.content.authentication_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "UserEmployeePosition")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEmployeePosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_employee_position_id;

    @Column(name = "uuid",unique = true, nullable = false)
    private UUID uuid;

    @Column(name = "position_name", length = 100, nullable = false)
    private String position_name;

    @Column(name = "position_description")
    private String position_description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

    @OneToMany(mappedBy = "user_employee_position_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserEmployee> user_employees;
}
