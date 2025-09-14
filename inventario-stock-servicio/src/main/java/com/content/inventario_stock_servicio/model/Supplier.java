package com.content.inventario_stock_servicio.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "Supplier")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "supplier_name", nullable = false, length = 100)
    private String name_Supplier;

    @Column(name = "ruc", nullable = false, length = 11)
    private String ruc;

    @Column(name = "cell", nullable = false, length = 9)
    private String cell;

    @OneToMany(mappedBy = "supplier_id", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<MatterSupplier> MatterSupplier;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="state_id", nullable = false)
    private State State;
}
