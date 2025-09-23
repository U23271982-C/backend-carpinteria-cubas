package com.content.sale_service.dto.Request;

import com.content.sale_service.states.TypesStateEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SaleStateRequestDTO {
    @NotNull(message = "Tiene que haber un estado de venta")
    private TypesStateEntity type_state_current;
}
