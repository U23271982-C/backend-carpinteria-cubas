package com.content.inventario_stock_servicio.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Matter")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Matter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement en MySQL
    private Integer id;

    @Column(name = "matter_name", nullable = false, length = 100)
    private String name_Matter;

    @Column(name = "path_image", nullable = false, length = 100)
    private String path_image;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "matter_type_id", nullable = false)
    private MatterType MatterType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "matterstate_id", nullable = false)
    private MatterState MatterState;

    @Column(name = "description", nullable = false, length = 100)
    private String description;

    @Column(name = "cost", nullable = false)
    private double cost;

    @Column(name = "unit_measure", nullable = false)
    private double unit_measure;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="state_id", nullable = false)
    private State State;

    @OneToOne(mappedBy = "matter_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private MatterCustomized MatterCustomized;

    @OneToOne(mappedBy = "matter_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private MatterPrefabricated MatterPrefabricated;

    @OneToMany(mappedBy = "matter_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MovementStockPrefabricated> MovementStockPrefabricated;

    @OneToMany(mappedBy = "matter_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MovementStockCustomized> MovementStockCustomized;

    @OneToMany(mappedBy = "matter_id", cascade =CascadeType.ALL, fetch = FetchType.EAGER)
    private List<MatterSupplier> MatterSuppliers;
}
