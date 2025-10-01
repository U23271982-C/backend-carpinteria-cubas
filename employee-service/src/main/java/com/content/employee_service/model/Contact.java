package com.content.employee_service.model;

import jakarta.persistence.*;
import lombok.*;

/**
 *
 * Entidad que representa la información de contacto de un empleado.
 * Cada contacto está asociado a un empleado y a un estado.
 */

@Entity
@Table(name = "Contact")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement en MySQL
    private Integer contact_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee_id;

    @Column(name = "phone_number", nullable = false, length = 9)
    private String phone_number;
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

}
