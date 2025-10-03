package com.content.inventory_matter_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

/**
 * Entidad que representa la materia prima.
 * Con sus respectivas columnas y relaciones.
 * Es la tabla centra de este modulo.
 */
@Entity
@Table(name = "Matter")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Matter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "matter_name", nullable = false, length = 100)
    private String matter_name;

    @Column(name = "path_image", nullable = false, length = 100)
    private String path_image;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "matter_type_id", nullable = false)
    private MatterType matter_type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "matter_state_id", nullable = false)
    private MatterState matter_state;

    @Column(name = "description", nullable = false, length = 100)
    private String description;

    @Column(name = "cost", nullable = false)
    private double cost;

    @Column(name = "unit_measure", nullable = false)
    private double unit_measure;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="state_id", nullable = false)
    private StateEntity state_entity;

    // ✅ Correct bidirectional One-to-One
    @OneToOne(mappedBy = "matter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private MatterCustomized matter_customized;

    @OneToOne(mappedBy = "matter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private MatterPrefabricated matter_prefabricated;

    @OneToMany(mappedBy = "matter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MovementStockPrefabricated> movement_stock_prefabricated;

    @OneToMany(mappedBy = "matter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MovementStockCustomized> movement_stock_customized;

    @OneToMany(mappedBy = "matter", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<MatterSupplier> matter_supplier;
}

