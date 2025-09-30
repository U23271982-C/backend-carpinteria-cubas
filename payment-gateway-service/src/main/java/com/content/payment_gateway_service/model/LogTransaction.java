package com.content.payment_gateway_service.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad que representa un registro de transacción en el sistema.
 * Incluye detalles como el endpoint utilizado, la IP del procesador y el tiempo de respuesta.
 * Está relacionada con un intento de pago y un estado específico.
 */

@Entity
@Table(name = "LogTransaction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer log_transaction_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attempt_id", nullable = false)
    private AttemptPayment attempt_id;

    @Column(name = "end_point", nullable = false, length = 255)
    private String end_point;
    @Column(name = "processor_ip", nullable = false, length = 45)
    private String processor_ip;
    @Column(name = "response_time", nullable = false)
    private Long response_time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_entity_id", nullable = false)
    private StateEntity state_entity_id;

}

















/*
TransactionLog log = TransactionLog.builder()
    .paymentAttempt(paymentAttempt)
    .endpoint("/api/payments")
    .processorIp("192.168.1.10")
    .responseTimeMs(120L)
    .build();

transactionLogRepository.save(log); Esto insertaria un nuevo registro en la tabla Payment con las columnas id,attempt_id y endpoint
*/
