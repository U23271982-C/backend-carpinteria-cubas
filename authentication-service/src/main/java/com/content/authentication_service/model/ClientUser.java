package com.content.authentication_service.model;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ClientUser")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ClientUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email", nullable = false, length = 100)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "registrationDate")
    private LocalDateTime registrationDate;
    @Column(name = "lastAccessDate")
    private LocalDateTime lastAccessDate;
    @Column(name = "passwordChangeDate")
    private LocalDateTime passwordChangeDate;
    @Column(name = "cancellationDate")
    private LocalDateTime cancellationDate;
    @Column(name = "marketingConsent")
    private Boolean marketingConsent;

}
