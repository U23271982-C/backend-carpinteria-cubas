package com.content.customer_service.model;

import com.content.customer_service.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Entidad que representa un contacto de un cliente.
 * ACTUALIZADA para usar SuperBuilder y herencia UUID
 */

@Entity
@Table(name ="Contact")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder // Cambiado de @Builder a @SuperBuilder
public class Contact extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer contact_id; // ID interno para la base de datos

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
