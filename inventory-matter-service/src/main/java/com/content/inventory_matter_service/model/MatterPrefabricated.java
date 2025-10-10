package com.content.inventory_matter_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "MatterPrefabricated")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatterPrefabricated {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer matter_prefabricated_id;

    @OneToOne
    @JoinColumn(name = "matter_id", nullable = false, unique = true)
    private Matter matter;

    @Column(name = "currently_stock", nullable = false)
    private Integer currently_stock;

    @Column(name = "min_stock", nullable = false)
    private Integer min_stock;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="state_entity_id", nullable = false)
    private StateEntity state_entity;
}

