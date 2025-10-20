package com.content.payment_gateway_service.dto.request;

import com.content.payment_gateway_service.model.*;
import lombok.*;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethodDTO {

    private Integer payment_method_id;
    private String payment_method_name;
    private String description;
    private StateEntity state_entity_id;
    private List<AttemptPayment> attemptPayments;
}
