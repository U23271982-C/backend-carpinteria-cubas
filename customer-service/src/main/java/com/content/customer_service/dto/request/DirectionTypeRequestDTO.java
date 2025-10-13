package com.content.customer_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO para la solicitud de creación/actualización de un Tipo de Dirección.
 * Solo incluye los campos que deben ser proporcionados manualmente desde el frontend.
 */
@Getter
@Setter
@Builder
public class DirectionTypeRequestDTO {

    // Descripción del tipo de dirección (ejemplo: "Casa", "Oficina", "Trabajo", "Otro")
    @NotBlank(message = "La descripción del tipo de dirección no debe estar vacía")
    @Size(max = 100, message = "La descripción no debe exceder los 100 caracteres")
    private String description;

}
