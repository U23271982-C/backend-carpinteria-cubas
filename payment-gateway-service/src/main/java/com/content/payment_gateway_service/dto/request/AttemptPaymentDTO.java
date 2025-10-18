package com.content.payment_gateway_service.dto.request;

import com.content.payment_gateway_service.model.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class AttemptPaymentDTO {

    @Positive(message = "")
    @NotNull(message = "")
    private Integer attempt_payment_id;


    private Payment payment_id;

    private PaymentMethod payment_method_id;

    private PaymentWay payment_way_id;

    private LocalDateTime payment_date;

    private StateEntity state_entity_id;

    private List<LogTransaction> logTransactions;
}
