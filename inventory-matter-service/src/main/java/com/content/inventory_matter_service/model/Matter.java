package com.content.inventory_matter_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
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
    private Integer matter_id;

    @Column(name = "matter_name", nullable = false, length = 100)
    private String matter_name;

    @Column(name = "description", nullable = false, length = 100)
    private String description;

    @Column(name = "path_image", nullable = false, length = 100)
    private String path_image;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "matter_type_id", nullable = false)
    private MatterType matter_type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "matter_state_id", nullable = false)
    private MatterState matter_state;

    @Column(name = "cost", nullable = false)
    private BigDecimal cost;

    @Column(name = "unit_measure", nullable = false)
    private double unit_measure;

    @Column(name = "general_stock", nullable = false)
    private int general_stock;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="state_entity_id", nullable = false)
    private StateEntity state_entity;

    //Relations
    @OneToOne(mappedBy = "matter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private MatterCustomized matter_customized;

    @OneToOne(mappedBy = "matter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private MatterPrefabricated matter_prefabricated;

    @OneToMany(mappedBy = "matter", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<MatterSupplier> matter_supplier;
}

