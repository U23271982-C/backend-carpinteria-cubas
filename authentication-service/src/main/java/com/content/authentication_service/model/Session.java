package com.content.authentication_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/**
 * Entidad que representa las sesiones de los usuarios en el sistema.
 * Incluye detalles como la fecha de la sesión, si fue exitosa y la dirección IP.
 * Las relaciones están configuradas para carga perezosa.
 */

@Entity
@Table(name = "Session")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer session_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clientsession_id", nullable = false)
    private User user_id;

    @Column(name = "session_date", nullable = false)
    private LocalDateTime session_date;
    @Column(name = "success", nullable = false)
    private boolean success;
    @Column(name = "address_ip", length = 16)
    private String address_ip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

}
