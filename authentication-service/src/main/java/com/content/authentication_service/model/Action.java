package com.content.authentication_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    @Column(name="action_name", length = 50)
    private String action_name;

    @Column(name="action_description", length = 100)
    private String action_description;

    @OneToMany(mappedBy = "action_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserAccessAction> user_access_actions;

}
