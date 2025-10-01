package com.content.employee_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

/**
 *
 * Entidad que representa un cargo dentro de la organización.
 * Cada cargo está asociado a un estado y puede tener múltiples empleados.
 */

@Entity
@Table(name = "Position")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement en MySQL
    private Integer post_id;

    @Column(name = "post_name", nullable = false, length = 100)
    private String post_name;
    @Column(name = "description", nullable = false, length = 250)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

    @OneToMany(mappedBy = "post_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Employee> employees;

}
