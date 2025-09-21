package com.content.payment_gateway_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;

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
    private Integer id;

    @Column(name = "uuid", nullable = false, unique = true, length = 36)
    private String uuid;
    @Column(name = "order_dd", nullable = false)
    private Integer orderId;
    @Column(name = "client_id", nullable = false)
    private Integer clientId;
    @Column(name = "paymentstatus", nullable = false, length = 50)
    private String paymentStatus;
    @Column(name = "total", nullable = false)
    private BigDecimal totalAmount;
    @Column(name = "currency", nullable = false, length = 10)
    private String currency;
    @Column(name = "processingdate")
    private LocalDateTime processingDate;
    @Column(name = "cancellationdate")
    private LocalDateTime cancellationDate;
    @Column(name = "description", length = 255)
    private String description;


}
