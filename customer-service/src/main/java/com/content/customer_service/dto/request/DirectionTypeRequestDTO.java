package com.content.customer_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO para la solicitud de creación de un Tipo de Dirección.
 */

@Getter
@Setter
@Builder
public class DirectionTypeRequestDTO {

    // Nombre del tipo de dirección
    @NotBlank(message = "El nombre del tipo de dirección no debe estar vacío")
    @Size(min = 3, max = 50, message = "El nombre del tipo de dirección debe tener entre 3 y 50 caracteres")
    private String direction_type_name;

    // Descripción del tipo de dirección
    @Size(max = 255, message = "La descripción no debe exceder los 255 caracteres")
    @NotBlank(message = "La descripción no debe estar vacía")
    private String description;

}
