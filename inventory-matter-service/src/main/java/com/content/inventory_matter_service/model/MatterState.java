package com.content.inventory_matter_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "MatterState")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatterState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer matter_state_id;

    @Column(name = "matter_state_name", nullable = false)
    private String matter_state_name;

    // mappedBy debe ser el nombre del atributo en Matter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="state_entity_id", nullable = false)
    private StateEntity state_entity;

    @OneToMany(mappedBy = "matter_state", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Matter> matter;
}

