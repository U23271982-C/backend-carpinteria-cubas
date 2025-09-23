package com.content.sale_service.dto.Request;

import com.content.sale_service.states.TypesStateEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SaleStateRequestDTO {
    private TypesStateEntity type_state_current;
}
