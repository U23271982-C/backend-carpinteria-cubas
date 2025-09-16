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

    // mappedBy debe coincidir con el atributo en Matter
    @OneToMany(mappedBy = "matterType", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Matter> matters;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="state_id", nullable = false)
    private State state;
}
