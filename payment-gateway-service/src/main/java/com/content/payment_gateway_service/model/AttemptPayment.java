package com.content.payment_gateway_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Entidad que representa un intento de pago realizado en el sistema.
 * Incluye detalles como la fecha del pago y las relaciones con el pago, el método de pago y la forma de pago.
 */

@Entity
@Table(name = "AttemptPayment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttemptPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer attempt_payment_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_method_id", nullable = false)
    private PaymentMethod payment_method_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_way_id", nullable = false)
    private PaymentWay payment_way_id;

    @Column(name = "payment_date", nullable = false)
    private LocalDateTime payment_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

    @OneToMany(mappedBy = "attempt_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<LogTransaction> logTransactions;

}
