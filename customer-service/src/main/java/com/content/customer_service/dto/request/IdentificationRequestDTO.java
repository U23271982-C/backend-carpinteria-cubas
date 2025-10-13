package com.content.customer_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO para la solicitud de creación/actualización de una Identificación.
 * Solo incluye los campos que deben ser proporcionados manualmente desde el frontend.
 */
@Getter
@Setter
@Builder
public class IdentificationRequestDTO {

    // Número o código del documento de identificación
    @NotBlank(message = "El documento de identificación no debe estar vacío")
    @Size(max = 50, message = "El documento de identificación no debe exceder los 50 caracteres")
    private String identification_doc;

    // ID del tipo de identificación (DNI, RUC, Pasaporte, etc.)
    @Positive(message = "El ID del tipo de identificación debe ser un número positivo")
    @NotNull(message = "El ID del tipo de identificación no debe ser nulo")
    private Integer identification_type_id;

}
