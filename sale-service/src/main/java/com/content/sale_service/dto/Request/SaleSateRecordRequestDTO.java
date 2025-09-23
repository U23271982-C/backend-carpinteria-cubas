package com.content.sale_service.dto.Request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SaleSateRecordRequestDTO {
    @NotNull(message = "El id del estado de venta no puede ser nulo")
    @Positive(message = "El id del estado de venta no puede ser negativo")
    private int id_sale_sate;
    @NotNull(message = "El id de la venta no puede ser nulo")
    @Positive(message = "El id de la venta no puede ser negativo")
    private int id_sale;
    @Size(max = 200, message = "El motivo de cambio de estado de una venta, esta limitado a 200 carácteres")
    private String motive;
}
