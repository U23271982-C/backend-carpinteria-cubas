package com.content.employee_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * Data Transfer Object para la entidad District.
 * Este DTO incluye lista de las entidades relaciones.
 */

@Getter
@Setter
@Builder
public class DistrictRequestDTO {

    // Nombre del distrito
    @NotBlank(message = "El nombre del distrito no puede estar vacío")
    @Size(min = 1, max = 100, message = "El nombre del distrito debe tener entre 1 y 100 caracteres")
    private String district_name;

}
