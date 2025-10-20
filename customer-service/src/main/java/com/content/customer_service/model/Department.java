package com.content.customer_service.model;

import com.content.customer_service.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

/**
 * Entidad que representa un departamento.
 */

@Entity
@Table(name = "Department")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Department extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer department_id; // ID interno para la base de datos

    @Column(name = "department_name", nullable = false, length = 100)
    private String department_name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

    @OneToMany(mappedBy = "department_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Province> provinces;
}
