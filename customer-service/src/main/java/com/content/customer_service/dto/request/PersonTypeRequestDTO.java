package com.content.customer_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO para la solicitud de creación/actualización de un Tipo de Persona.
 * Solo incluye los campos que deben ser proporcionados manualmente desde el frontend.
 */

@Getter
@Setter
@Builder
public class PersonTypeRequestDTO {

    // Nombre del tipo de persona (ejemplo: "Natural", "Jurídica")
    @NotBlank(message = "El nombre del tipo de persona no debe estar vacío")
    @Size(max = 100, message = "El nombre del tipo de persona no debe exceder los 100 caracteres")
    private String persona_type_name;

}
