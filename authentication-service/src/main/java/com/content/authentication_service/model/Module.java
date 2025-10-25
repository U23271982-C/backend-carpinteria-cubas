package com.content.authentication_service.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "Module")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer module_id;

    @Column(name="uuid", nullable = false, unique = true, updatable = false)
    private UUID uuid;

    @Column(name="module_name", length = 50)
    private String name;

    @Column(name="module_description", length = 100)
    private String module_description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

    @OneToMany(mappedBy = "module_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserModuleAccess> user_module_accesses;
}
