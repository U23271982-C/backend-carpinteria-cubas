package com.content.employee_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * Data Transfer Object para la entidad PersonType.
 * Este DTO incluye lista de las entidades relaciones.
 */

@Getter
@Setter
@Builder
public class PersonTypeRequestDTO {

    // Nombre del tipo de persona
    @Size(min = 1, max = 100)
    @NotBlank(message = "El nombre del tipo de persona no puede estar en blanco")
    private String person_type_name;

}
