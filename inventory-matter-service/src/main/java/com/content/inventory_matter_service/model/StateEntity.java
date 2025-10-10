package com.content.inventory_matter_service.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "StateEntity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "state", nullable = false, length = 100)
    private String name_State;

    // Relations
    @OneToMany(mappedBy = "state_entity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Matter> matters;

    @OneToMany(mappedBy = "state_entity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MatterCustomized> matters_customized;

    @OneToMany(mappedBy = "state_entity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MatterPrefabricated> matters_prefabricated;

    @OneToMany(mappedBy = "state_entity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MatterState> matter_states;

    @OneToMany(mappedBy = "state_entity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MatterSupplier> matters_supplier;

    @OneToMany(mappedBy = "state_entity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MatterType> matters_type;

    @OneToMany(mappedBy = "state_entity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Supplier> suppliers;

    @OneToMany(mappedBy = "state_entity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SupplierType> supplier_type;
}

