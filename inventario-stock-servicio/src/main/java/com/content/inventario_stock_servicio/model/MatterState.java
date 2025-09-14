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

    @Column(name = "matterState_name", nullable = false)
    private String matterState_name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="state_id", nullable = false)
    private State State;

    @OneToMany(mappedBy = "matterstate_id" , cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Matter> matters;
}
