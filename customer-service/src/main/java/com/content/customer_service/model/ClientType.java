package com.content.customer_service.model;

import com.content.customer_service.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Entidad que representa el tipo de cliente.
 */

@Entity
@Table(name = "ClientType")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ClientType extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer client_type_id; // ID interno para la base de datos

    @Column(name = "client_type_name", nullable = false, length = 50)
    private String client_type_name;

    @Column(name = "description", length = 255)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

    @OneToMany(mappedBy = "client_type_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Client> clients;
}
