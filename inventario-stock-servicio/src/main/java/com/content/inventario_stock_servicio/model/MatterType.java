package com.content.inventario_stock_servicio.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "MatterType")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatterType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "mattertype_name", nullable = false)
    private String mattertype_name;

    @OneToMany(mappedBy = "matter_id",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Matter> Matter;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="state_id", nullable = false)
    private State state;

}
