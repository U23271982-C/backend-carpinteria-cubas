package com.content.payment_gateway_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.List;

/**
 * Entidad que representa un pago realizado en el sistema.
 * Incluye detalles como el total, la moneda, las fechas de procesamiento y finalización,
 * así como las relaciones con el estado del pago y los intentos de pago asociados.
 */

@Entity
@Table(name = "Payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer payment_id;

    @Column(name = "uuid", nullable = false, unique = true, length = 100)
    private String uuid;
    @Column(name= "permit_id", nullable = false)
    private Integer permit_id;
    @Column(name = "client_id", nullable = false)
    private Integer client_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_state_id", nullable = false)
    private PaymentState payment_state_id;

    @Column(name = "total", nullable = false)
    private BigDecimal total;
    @Column(name = "currency", nullable = false, length = 100)
    private String currency;
    @Column(name = "processing_date", nullable = false)
    private LocalDateTime processing_date;
    @Column(name = "finish_date", nullable = false)
    private LocalDateTime finish_date;
    @Column(name = "description", length = 255)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

    @OneToMany(mappedBy = "payment_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AttemptPayment> attemptPayments;

}
