package com.content.inventario_stock_servicio.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "MovementType")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovementType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "movement_type_name", nullable = false)
    private String movement_type_name;

    @OneToMany(mappedBy = "movementstockprefabricated_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MovementStockPrefabricated> MovementStockPrefabricated;

    @OneToMany(mappedBy = "movementstockcustomized_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MovementStockCustomized> MovementStockCustomized;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="state_id", nullable = false)
    private State State;
}
