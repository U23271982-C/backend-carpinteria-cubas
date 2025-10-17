package com.content.payment_gateway_service.dto.response;

import com.content.payment_gateway_service.model.StateEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentWayResponseDTO {

    private Integer payment_way_id;
    private String payment_way_name;
    private String description;
    private StateEntity state_entity_id;
}
