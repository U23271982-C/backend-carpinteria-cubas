package com.content.customer_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

/**
 * Entidad que representa un distrito.
 * Contiene información sobre el nombre del distrito, su provincia y su estado.
 */

@Entity
@Table(name ="District")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer district_id;

    @Column(name = "district_name", nullable = false, length = 100)
    private String district_name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province_id", nullable = false)
    private Province province_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

    @OneToMany(mappedBy = "district_id", fetch = FetchType.LAZY)
    private List<Direction> directions;

}
