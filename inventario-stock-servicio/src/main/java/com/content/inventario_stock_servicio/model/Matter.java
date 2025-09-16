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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "matter_name", nullable = false, length = 100)
    private String name_Matter;

    @Column(name = "path_image", nullable = false, length = 100)
    private String path_image;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "matter_type_id", nullable = false)
    private MatterType matterType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "matterstate_id", nullable = false)
    private MatterState matterState;

    @Column(name = "description", nullable = false, length = 100)
    private String description;

    @Column(name = "cost", nullable = false)
    private double cost;

    @Column(name = "unit_measure", nullable = false)
    private double unit_measure;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="state_id", nullable = false)
    private State state;

    // ✅ Correct bidirectional One-to-One
    @OneToOne(mappedBy = "matter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private MatterCustomized matterCustomized;

    @OneToOne(mappedBy = "matter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private MatterPrefabricated matterPrefabricated;

    @OneToMany(mappedBy = "matter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MovementStockPrefabricated> movementStockPrefabricated;

    @OneToMany(mappedBy = "matter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MovementStockCustomized> movementStockCustomized;

    @OneToMany(mappedBy = "matter", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<MatterSupplier> matterSuppliers;
}

