package com.content.customer_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

/**
 * Entidad que representa un departamento.
 * Contiene información sobre el nombre del departamento, su estado y las provincias asociadas.
 */

@Entity
@Table(name ="Department")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer department_id;

    @Column(name = "department_name", nullable = false, length = 100)
    private String department_name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

    @OneToMany(mappedBy = "department_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Province> provinces;

}
