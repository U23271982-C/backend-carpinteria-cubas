package com.content.inventory_matter_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "MatterType")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatterType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "mattertype_name", nullable = false)
    private String mattertype_name;

    // mappedBy debe coincidir con el atributo en Matter
    @OneToMany(mappedBy = "matter_type", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Matter> matter;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="state_id", nullable = false)
    private StateEntity state_entity;
}
