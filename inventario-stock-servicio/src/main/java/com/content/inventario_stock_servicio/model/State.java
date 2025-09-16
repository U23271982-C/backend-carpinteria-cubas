package com.content.inventario_stock_servicio.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "State")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement en MySQL
    private int id;
    @Column(name = "state", nullable = false, length = 100)
    private String name_State;

    @OneToMany(mappedBy = "state_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Matter> Matters;

    @OneToMany(mappedBy = "state_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MatterCustomized> Matterscustomized;

    @OneToMany(mappedBy = "state_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MatterPrefabricated> Mattersprefabricated;

    @OneToMany(mappedBy = "state_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MatterState> Matterstate;

    @OneToMany(mappedBy = "state_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MatterSupplier> Matterssupplier;

    @OneToMany(mappedBy = "state_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MatterType> Matterstype;

    @OneToMany(mappedBy = "state_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MovementStockCustomized> Movementstockcustomized;

    @OneToMany(mappedBy = "state_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MovementStockPrefabricated> Movementstockprefabricated;

    @OneToMany(mappedBy = "state_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MovementType> Movementtype;

    @OneToMany(mappedBy = "state_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Supplier> Supplier;
}
