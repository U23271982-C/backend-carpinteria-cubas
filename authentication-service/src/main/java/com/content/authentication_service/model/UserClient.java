package com.content.authentication_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/**
 * Entidad que representa a los clientes de usuario en el sistema.
 * Incluye detalles como nombre, fecha de nacimiento y edad.
 * Las relaciones están configuradas para carga perezosa y cascada en todas las operaciones.
 */

@Entity
@Table(name = "UserClient")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_client_id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @Column(unique = true, nullable = false)
    private User user_id;

    @Column(name = "user_client_name", nullable = false, length = 100)
    private String user_client_name;
    @Column(name = "user_client_birth_date", nullable = false, length = 100)
    private LocalDateTime user_client_birth_date;
    @Column(name = "user_client_age", nullable = false)
    private Integer user_client_age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

}
