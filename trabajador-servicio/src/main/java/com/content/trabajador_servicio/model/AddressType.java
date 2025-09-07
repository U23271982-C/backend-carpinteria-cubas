package com.content.trabajador_servicio.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "AddressType")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement en MySQL
    private Integer id;
    @Column(name = "address_type", nullable = false, length = 100)
    private String nameAddressType;
    @Column(name = "description", nullable = true, length = 255)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id", nullable = false)
    private State state;

    @OneToMany(mappedBy = "addressType", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Address> addresses;
}
