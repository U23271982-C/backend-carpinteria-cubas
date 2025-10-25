package com.content.authentication_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Action")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer action_id;

    @Column(name="uuid", nullable = false, unique = true, updatable = false)
    private UUID uuid;

    @Column(name="action_name", length = 50)
    private String action_name;

    @Column(name="action_description", length = 100)
    private String action_description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

    @OneToMany(mappedBy = "actionId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserAccessAction> user_access_actions;

}
