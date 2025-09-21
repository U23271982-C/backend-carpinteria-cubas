package com.content.payment_gateway_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class TransactionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attempt_id", nullable = false)
    private PaymentAttempt paymentAttempt;

    @Column(name = "endpoint", nullable = false, length = 255)
    private String endpoint;
    @Column(name = "processorpp", nullable = false, length = 45)
    private String processorIp;
    @Column(name = "responseTimeMs_id", nullable = false)
    private Long responseTimeMs;

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
