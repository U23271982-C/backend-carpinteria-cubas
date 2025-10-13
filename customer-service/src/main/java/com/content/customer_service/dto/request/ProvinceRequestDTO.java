package com.content.customer_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO para la solicitud de creación/actualización de una Provincia.
 * Solo incluye los campos que deben ser proporcionados manualmente desde el frontend.
 * Nota: La provincia se crea automáticamente si no existe al registrar una dirección.
 */

@Getter
@Setter
@Builder
public class ProvinceRequestDTO {

    // Nombre de la provincia (ejemplo: "Lima", "Callao", "Cañete")
    @NotBlank(message = "El nombre de la provincia no debe estar vacío")
    @Size(max = 100, message = "El nombre de la provincia no debe exceder los 100 caracteres")
    private String province_name;

    // ID del departamento asociado
    @Positive(message = "El ID del departamento debe ser un número positivo")
    @NotNull(message = "El ID del departamento no debe ser nulo")
    private Integer department_id;

}
