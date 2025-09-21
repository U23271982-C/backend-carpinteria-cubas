package com.content.authentication_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime; // para que me funcione el localdatetime

@Entity
@Table(name = "WorkingUser")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class WorkingUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email", nullable = false,length = 100)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "LocalDateTime ")
    private LocalDateTime creationDate;
    @Column(name = "lastAccessDate")
    private LocalDateTime lastAccessDate;
    @Column(name = "passwordChangeDate")
    private LocalDateTime passwordChangeDate;
    @Column(name = "failedAttempts")
    private Integer failedAttempts;
    @Column(name = "state", nullable = false)
    private Boolean active;
    @Column(name = "createdBy", length = 100)
    private String createdBy;

}
