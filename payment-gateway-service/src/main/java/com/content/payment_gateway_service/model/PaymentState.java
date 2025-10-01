package com.content.payment_gateway_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

/**
 * Entidad que representa los diferentes estados de un pago en el sistema.
 * Las relaciones están configuradas para carga perezosa y cascada en todas las operaciones.
 */

@Entity
@Table(name = "PaymentState")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer payment_state_id;

    @Column(name = "payment_state", nullable = false, length = 100)
    private String payment_state_name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

    @OneToMany(mappedBy = "payment_state_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Payment> payments;

}
