package com.content.payment_gateway_service.dto.request;

import com.content.payment_gateway_service.model.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {

    private Integer payment_id;
    private String uuid;
    private Integer permit_id;
    private Integer client_id;
    private PaymentState payment_state_id;
    private BigDecimal total;
    private String currency;
    private LocalDateTime processing_date;
    private LocalDateTime finish_date;
    private String description;
    private StateEntity state_entity_id;
    private List<AttemptPayment> attemptPayments;
}
