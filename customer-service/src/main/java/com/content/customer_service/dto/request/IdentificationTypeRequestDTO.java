package com.content.customer_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO para la solicitud de creación/actualización de un Tipo de Identificación.
 * Solo incluye los campos que deben ser proporcionados manualmente desde el frontend.
 */

@Getter
@Setter
@Builder
public class IdentificationTypeRequestDTO {

    // Nombre del tipo de identificación (ejemplo: "DNI", "RUC", "Pasaporte", "Carnet de Extranjería")
    @NotBlank(message = "El nombre del tipo de identificación no debe estar vacío")
    @Size(max = 100, message = "El nombre del tipo de identificación no debe exceder los 100 caracteres")
    private String identification_type_name;

    // ID del tipo de persona asociada (Natural, Jurídica)
    @Positive(message = "El ID del tipo de persona debe ser un número positivo")
    @NotNull(message = "El ID del tipo de persona no debe ser nulo")
    private Integer person_type_id;

}
