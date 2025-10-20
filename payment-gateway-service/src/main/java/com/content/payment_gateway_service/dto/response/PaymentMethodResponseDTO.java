package com.content.payment_gateway_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethodResponseDTO {
    private int paymentWayId;
    private String paymentWayName;
    private String description;
}
