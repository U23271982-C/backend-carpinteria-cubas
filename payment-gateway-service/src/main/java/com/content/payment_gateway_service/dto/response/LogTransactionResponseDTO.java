package com.content.payment_gateway_service.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogTransactionResponseDTO {
    private int logTransactionId;
    private String endPoint;
    private String processorIp;
    private Long responseTime;

}
