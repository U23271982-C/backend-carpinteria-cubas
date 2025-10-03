package com.content.inventory_matter_service.model;
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

    // mappedBy debe ser el atributo en MovementStockPrefabricated
    @OneToMany(mappedBy = "movement_type", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MovementStockPrefabricated> movement_stock_prefabricated;

    // mappedBy debe ser el atributo en MovementStockCustomized
    @OneToMany(mappedBy = "movement_type", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MovementStockCustomized> movement_stock_customized;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="state_id", nullable = false)
    private StateEntity state_entity;
}
