package com.content.customer_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

/**
 * Entidad que representa una identificación de un cliente.
 * Contiene el valor de la identificación, su tipo y su estado.
 */

@Entity
@Table(name ="Identification")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Identification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer identification_id;

    @Column(name = "identification_doc", nullable = false, length = 50)
    private String identification_doc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "identification_type_id", nullable = false)
    private IdentificationType identification_type_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

    @OneToMany(mappedBy = "identification_id", fetch = FetchType.LAZY)
    private List<Client> clients;

}
