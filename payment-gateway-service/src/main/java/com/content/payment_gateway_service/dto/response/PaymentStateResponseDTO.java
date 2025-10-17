package com.content.payment_gateway_service.dto.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentStateResponseDTO {
    private int paymentStateId;
    private String paymentStateName;

}
