package com.content.customer_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO para la solicitud de creación/actualización de un Distrito.
 * Solo incluye los campos que deben ser proporcionados manualmente desde el frontend.
 * Nota: El distrito se crea automáticamente si no existe al registrar una dirección.
 */

@Getter
@Setter
@Builder
public class DistrictRequestDTO {

    // Nombre del distrito (ejemplo: "Miraflores", "San Isidro", "Barranco")
    @NotBlank(message = "El nombre del distrito no debe estar vacío")
    @Size(max = 100, message = "El nombre del distrito no debe exceder los 100 caracteres")
    private String district_name;

    // ID de la provincia asociada
    @Positive(message = "El ID de la provincia debe ser un número positivo")
    @NotNull(message = "El ID de la provincia no debe ser nulo")
    private Integer province_id;

}
