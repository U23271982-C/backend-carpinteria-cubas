package com.content.payment_gateway_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseDTO {
    private int paymentId;
    private String uuid;
    private int permitId;
    private int clientId;
    private BigDecimal total;
    private String currency;
    private LocalDateTime processingDate;
    private LocalDateTime finishDate;
    private String description;
    private PaymentStateResponseDTO paymentState;
    private List<AttemptPaymentResponseDTO> attemptPayments;
}
