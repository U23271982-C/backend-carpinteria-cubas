package com.content.inventario_stock_servicio.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "MatterState")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatterState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "matterstate_name", nullable = false)
    private String matterstate_name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="state_id", nullable = false)
    private State state;

    @OneToMany(mappedBy = "matterstate_id" , cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Matter> matters;
}
