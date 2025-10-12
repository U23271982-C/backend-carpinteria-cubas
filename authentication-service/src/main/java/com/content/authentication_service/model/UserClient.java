package com.content.authentication_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

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

    @Column(name = "fire_base_uid", nullable = false, unique = true, length = 128)
    private String fireBaseUid;

    @Column(name = "userclient_email", length = 100)
    private String user_client_email;

    @Column(name="userclient_full_name", length = 100)
    private String user_client_full_name;

    @Column(name = "user_client_phone", length = 20)
    private String user_client_phone;

    @Column(name = "user_client_address")
    private String user_client_address;

    @Column(name = "user_client_created_at", nullable = false)
    private LocalDateTime user_client_created_at;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;
}
