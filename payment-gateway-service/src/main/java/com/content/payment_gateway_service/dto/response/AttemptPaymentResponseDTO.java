package com.content.payment_gateway_service.dto.response;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttemptPaymentResponseDTO {

    private int attemptPaymentId;
    private LocalDateTime paymentDate;
    private int paymentId;
    private PaymentMethodResponseDTO paymentMethod;
    private PaymentWayResponseDTO paymentWay;
    private List<LogTransactionResponseDTO> logTransactions;
}
