package com.content.customer_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO para la solicitud de creación de un Tipo de Identificación.
 */

@Getter
@Setter
@Builder
public class IdentificationTypeRequestDTO {

    // Nombre del tipo de identificación
    @NotBlank(message = "El nombre del Tipo de Identificación no debe estar vacío")
    @Size(max = 50, message = "El nombre del Tipo de Identificación no debe exceder los 50 caracteres")
    private String identification_type_name;

    // ID del Tipo de Persona asociado al Tipo de Identificación
    @Positive(message = "El ID del Tipo de Persona debe ser un número positivo")
    @NotNull(message = "El ID del Tipo de Persona no debe ser nulo")
    private Integer person_type_id;

}
