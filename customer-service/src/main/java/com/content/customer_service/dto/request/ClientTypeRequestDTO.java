package com.content.customer_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO para la solicitud de creación de un Tipo de Cliente.
 */

@Getter
@Setter
@Builder
public class ClientTypeRequestDTO {

    // Nombre del tipo de cliente
    @NotBlank(message = "El nombre del tipo de cliente no debe estar vacío")
    @Size(min = 3, max = 50, message = "El nombre del tipo de cliente debe tener entre 3 y 50 caracteres")
    private String client_type_name;

    // Descripción del tipo de cliente
    @NotBlank(message = "La descripción no debe estar vacía")
    @Size(min = 5, max = 255, message = "La descripción debe tener entre 5 y 255 caracteres")
    private String description;

}
