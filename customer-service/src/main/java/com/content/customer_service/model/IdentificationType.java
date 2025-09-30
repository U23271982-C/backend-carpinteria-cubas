package com.content.customer_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

/**
 * Entidad que representa los tipos de identificación.
 * Contiene información sobre el tipo de identificación y su estado.
 */

@Entity
@Table(name ="IdentificationType")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IdentificationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer identification_type_id;

    @Column(name = "identification_type_name", nullable = false, length = 100)
    private String identification_type_name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_type_id", nullable = false)
    private PersonType personType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

    @OneToMany(mappedBy = "identification_type_id", fetch = FetchType.LAZY)
    private List<Identification> identifications;

}
