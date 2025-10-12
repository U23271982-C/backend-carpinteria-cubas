package com.content.customer_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO para la solicitud de creación de un Distrito.
 */

@Getter
@Setter
@Builder
public class DistrictRequestDTO {

    // Nombre del distrito
    @NotBlank(message = "El nombre del distrito no debe estar vacío")
    @Size(min = 3, max = 50, message = "El nombre del distrito debe tener entre 3 y 50 caracteres")
    private String district_name;

    // ID de la Provincia asociada
    @Positive(message = "El ID de la Provincia debe ser un número positivo")
    @NotNull(message = "El ID de la Provincia no debe ser nulo")
    private Integer province_id;

}
