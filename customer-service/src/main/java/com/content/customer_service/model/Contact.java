package com.content.customer_service.model;

import jakarta.persistence.*;

import lombok.*;

/**
 * Entidad que representa un contacto de un cliente.
 * Contiene información de contacto como número de teléfono y correo electrónico.
 */

@Entity
@Table(name ="Contact")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer contact_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client_id;

    @Column(name = "phone_number", nullable = false, length = 20)
    private String phone_number;
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

}
