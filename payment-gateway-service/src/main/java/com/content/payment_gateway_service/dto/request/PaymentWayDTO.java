package com.content.payment_gateway_service.dto.request;

import com.content.payment_gateway_service.model.*;
import lombok.*;


import java.util.List;
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PaymentWayDTO {

    private Integer payment_way_id;
    private String payment_way_name;
    private String description;
    private StateEntity state_entity_id;
    private List<AttemptPayment> attemptPayments;
}
