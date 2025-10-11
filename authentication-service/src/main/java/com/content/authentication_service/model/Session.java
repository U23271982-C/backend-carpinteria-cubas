package com.content.authentication_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

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
    @JoinColumn(name = "user_employee_id", nullable = false)
    private UserEmployee user_employee_id;

    @Column(name = "sesion_date")
    private LocalDateTime sesion_date;

    @Column(name="succesed")
    private Boolean succesed;

    @Column(name="ip_address", length = 100)
    private String ip_address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;
}
