package com.content.customer_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO para la solicitud de creación/actualización de un Tipo de Cliente.
 * Solo incluye los campos que deben ser proporcionados manualmente desde el frontend.
 */

@Getter
@Setter
@Builder
public class ClientTypeRequestDTO {

    // Nombre del tipo de cliente (ejemplo: "Premium", "Regular", "VIP")
    @NotBlank(message = "El nombre del tipo de cliente no debe estar vacío")
    @Size(max = 50, message = "El nombre del tipo de cliente no debe exceder los 50 caracteres")
    private String client_type_name;

    // Descripción del tipo de cliente
    @NotBlank(message = "La descripción no debe estar vacía")
    @Size(max = 100, message = "La descripción no debe exceder los 100 caracteres")
    private String description;

}
