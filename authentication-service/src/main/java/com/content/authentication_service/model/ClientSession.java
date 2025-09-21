package com.content.authentication_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "ClientSession")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ClientSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clientsession_id", nullable = false)
    private ClientUser clientUser;
    @Column(name = "startTime")
    private LocalDateTime startTime;
    @Column(name = "endTime")
    private LocalDateTime endTime;
    @Column(name = "ipaddress", length = 45)
    private String ipAddress;
    @Column(name = "userAgent", length = 255)
    private String userAgent;

}
