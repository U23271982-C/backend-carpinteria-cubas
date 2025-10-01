package com.content.customer_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

/**
 * Entidad que representa un tipo de cliente.
 * Contiene el nombre del tipo de cliente y su descripción.
 */

@Entity
@Table(name ="ClientType")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer client_type_id;

    @Column(name = "client_type_name", nullable = false, length = 50)
    private String client_type_name;
    @Column(name = "descripcion", nullable = false, length = 100)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

    @OneToMany(mappedBy = "client_type_id", fetch = FetchType.LAZY)
    private List<Client> clients;

}
