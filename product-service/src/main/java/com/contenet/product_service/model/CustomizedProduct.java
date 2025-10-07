package com.contenet.product_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

/**
 * Entidad que representa un producto personalizado en el sistema.
 * Cada producto personalizado está asociado a un producto base y un estado (activo, inactivo, eliminado).
 */

@Entity
@Table(name = "CustomizedProduct")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomizedProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement en MySQL
    private Integer customized_product_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_base_id", nullable = false)
    private ProductBase product_base_id;

    @Column(name = "customized_product_specification", nullable = false, length = 100)
    private String specification;
    @Column(name = "stimated_time_hours", nullable = false)
    private Integer stimated_time_hours;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

    @OneToMany(mappedBy = "customized_product_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Production> productions ;

}
