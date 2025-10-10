package com.content.employee_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * Data Transfer Object para la entidad IdentificationType.
 * Este DTO incluye lista de las entidades relaciones.
 */

@Getter
@Setter
@Builder
public class IdentificationTypeRequestDTO {

    // Nombre del tipo de identificación
    @Size(min = 1, max = 100, message = "El nombre del tipo de identificación debe tener entre 1 y 100 caracteres")
    @NotBlank(message = "El nombre del tipo de identificación no puede estar vacío")
    private String identification_type_name;

    // ID del Tipo de Persona
    @Positive(message = "El id del estado de la entidad no puede ser negativo")
    @NotNull(message = "El id del tipo de persona no puede ser nulo")
    private Integer person_type_id;

}
