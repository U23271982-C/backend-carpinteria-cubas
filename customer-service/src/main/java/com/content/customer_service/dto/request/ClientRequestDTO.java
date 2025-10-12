package com.content.customer_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO para la solicitud de creación de un Cliente.
 */

@Getter
@Setter
@Builder
public class ClientRequestDTO {

    // Nombre del cliente
    @NotBlank(message = "El nombre del cliente no debe estar vacío")
    @Size(max = 100, message = "El nombre del cliente no debe exceder los 100 caracteres")
    private String client_name;

    // Apellido del cliente
    @NotBlank(message = "El apellido del cliente no debe estar vacío")
    @Size(max = 100, message = "El apellido del cliente no debe exceder los 100 caracteres")
    private String client_last_name;

    // ID del Tipo de Cliente asociado
    @Positive(message = "El ID del Tipo de Cliente debe ser un número positivo")
    @NotNull(message = "El ID del Tipo de Cliente no debe ser nulo")
    private Integer client_type_id;

    // ID de la Identificación asociada
    @Positive(message = "El ID de la Identificación debe ser un número positivo")
    @NotNull(message = "El ID de la Identificación no debe ser nulo")
    private Integer identification_id;

}
