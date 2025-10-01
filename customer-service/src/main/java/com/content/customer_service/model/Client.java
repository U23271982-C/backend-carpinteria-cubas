package com.content.customer_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Entidad que representa un cliente.
 * Contiene información personal y referencias a otros tipos relacionados.
 */

@Entity
@Table(name ="Client")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer client_id;

    @Column(name = "client_name", nullable = false, length = 100)
    private String client_name;
    @Column(name = "client_last_name", nullable = false, length = 100)
    private String client_last_name;
    @Column(name = "registration_date", nullable = false)
    private LocalDateTime registration_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_type_id", nullable = false)
    private ClientType client_type_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "identification_id", nullable = false)
    private Identification identification_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

    @OneToMany(mappedBy = "client_id", fetch = FetchType.LAZY)
    private List<Contact> contacts;
    @OneToMany(mappedBy = "client_id", fetch = FetchType.LAZY)
    private List<Direction> directions;

}
