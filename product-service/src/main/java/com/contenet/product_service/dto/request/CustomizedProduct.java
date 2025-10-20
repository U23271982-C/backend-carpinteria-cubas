package com.contenet.product_service.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Getter
@Setter
public class CustomizedProduct {
    @NotNull(message = "El ID del producto base no puede ser nulo.")
    @Positive(message = "El ID del producto base debe ser un número positivo.")
    private Integer product_base_id;

    @NotBlank(message = "Las especificaciones no pueden estar vacías.")
    @Size(max = 255)
    private String specifications;

    @NotNull(message = "El tiempo estimado no puede ser nulo.")
    @Positive(message = "El tiempo estimado en horas debe ser positivo.")
    private Integer stimatedTimeHours;
}
