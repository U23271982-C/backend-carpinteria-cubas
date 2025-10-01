package com.content.authentication_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Entidad que representa a los usuarios del sistema.
 * Incluye detalles como nombre de usuario, correo electrónico, contraseñas y fechas relevantes.
 * Las relaciones están configuradas para carga perezosa y cascada en todas las operaciones.
 */

@Entity
@Table(name = "User")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;

    @Column(name = "user_name", nullable = false, length = 100)
    private String user_name;
    @Column(name = "email", nullable = false, length = 100)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "register_date", nullable = false)
    private LocalDateTime register_date;
    @Column(name = "last_access_date", nullable = false)
    private LocalDateTime last_access_date;
    @Column(name = "change_password_date", nullable = false)
    private LocalDateTime change_password_date;
    @Column(name = "fialed_attempt", nullable = false)
    private LocalDateTime fialed_attempt;


    @Column(name = "created_by")
    private Integer created_by;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_type_id", nullable = false)
    private UserType user_type_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

    @OneToMany(mappedBy = "user_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserRole> user_roles;
    @OneToMany(mappedBy = "user_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Session> sessions;
    @OneToOne(mappedBy = "user_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private UserClient user_client ;
    @OneToOne(mappedBy = "user_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private UserEmployee user_employees ;

}
