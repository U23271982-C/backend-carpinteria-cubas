package com.content.inventario_stock_servicio.model;

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
    private Integer id;

    @Column(name = "matter_state_name", nullable = false)
    private String matter_state_name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="state_id", nullable = false)
    private StateEntity state_entity;

    // mappedBy debe ser el nombre del atributo en Matter
    @OneToMany(mappedBy = "matter_state", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Matter> matter;
}

