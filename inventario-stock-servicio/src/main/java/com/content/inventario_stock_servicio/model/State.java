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
    private List<Matter> matters;

    @OneToMany(mappedBy = "state_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MatterCustomized> matterscustomized;

    @OneToMany(mappedBy = "state_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MatterPrefabricated> mattersprefabricated;

    @OneToMany(mappedBy = "state_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MatterState> matterstate;

    @OneToMany(mappedBy = "state_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MatterSupplier> matterssupplier;

    @OneToMany(mappedBy = "state_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MatterType> matterstype;

    @OneToMany(mappedBy = "state_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MovementStockCustomized> movementstockcustomized;

    @OneToMany(mappedBy = "state_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MovementStockPrefabricated> movementstockprefabricated;

    @OneToMany(mappedBy = "state_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MovementType> movementtype;

    @OneToMany(mappedBy = "state_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Supplier> supplier;
}
