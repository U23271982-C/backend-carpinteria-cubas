package com.content.authentication_service.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_module_access_id", nullable = false)
    private UserModuleAccess user_module_access_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "action_id", nullable = false)
    private Action action_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;
}
