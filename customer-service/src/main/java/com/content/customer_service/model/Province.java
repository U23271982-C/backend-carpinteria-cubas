package com.content.customer_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

/**
 * Entidad que representa un departamento.
 * Contiene información sobre el nombre del departamento, su estado y las provincias asociadas.
 */

@Entity
@Table(name ="Province")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Province {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer province_id;

    @Column(name = "province_name", nullable = false, length = 100)
    private String province_name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

    @OneToMany(mappedBy = "province_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<District> districts;

}
