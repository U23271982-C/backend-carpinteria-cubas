package com.contenet.product_service.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Getter
@Setter
public class Production {
    @NotNull(message = "El ID del producto personalizado no puede ser nulo.")
    @Positive(message = "El ID del producto personalizado debe ser positivo.")
    private Integer customized_product_id;

    @NotNull(message = "El ID del estado de producción no puede ser nulo.")
    @Positive(message = "El ID del estado de producción debe ser positivo.")
    private Integer production_state_id;

    @NotNull(message = "El ID de la venta no puede ser nulo.")
    @Positive(message = "El ID de la venta debe ser positivo.")
    private Integer sale_id;
}
