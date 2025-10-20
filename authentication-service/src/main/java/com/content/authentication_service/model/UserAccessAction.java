package com.content.authentication_service.model;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "UserAccessAction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAccessAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_access_action_id;

    @Column(name="uuid", nullable = false, unique = true, updatable = false)
    private UUID uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_module_access_id", nullable = false)
    private UserModuleAccess user_module_access_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "action_id", nullable = false)
    private Action action_id;
}
