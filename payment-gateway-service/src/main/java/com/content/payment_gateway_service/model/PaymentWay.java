package com.content.payment_gateway_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

/**
 * Entidad que representa una forma de pago en el sistema.
 * Incluye detalles como el nombre y la descripción de la forma de pago.
 */

@Entity
@Table(name = "PaymentWay")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentWay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer payment_way_id;

    @Column(name = "payment_way_name",nullable = false, length = 100)
    private String payment_way_name;
    @Column(name = "description", length = 255)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

    @OneToMany(mappedBy = "payment_way_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AttemptPayment> attemptPayments;

}
