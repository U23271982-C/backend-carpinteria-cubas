package com.content.authentication_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "WorkerSession")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class WorkerSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workerUser", nullable = false)
    private WorkingUser workerUser;

    @Column(name = "startTime")
    private LocalDateTime startTime;
    @Column(name = "endTime")
    private LocalDateTime endTime;
    @Column(name = "ipAddress", length = 45)
    private String ipAddress;

}
