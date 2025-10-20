package com.content.payment_gateway_service.dto.request;

import com.content.payment_gateway_service.model.Payment;
import com.content.payment_gateway_service.model.StateEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class PaymentStateDTO {

    private Integer payment_state_id;

    @NotBlank(message = "El nombre del estado de pago no puede estar vacío.")
    @Size(max = 100)
    private String payment_state_name;

    private StateEntity state_entity_id;

    private List<Payment> payments;
}
