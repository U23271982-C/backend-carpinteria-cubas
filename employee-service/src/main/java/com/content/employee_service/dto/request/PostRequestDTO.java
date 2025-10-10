package com.content.employee_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * Data Transfer Object para la entidad Position.
 * Este DTO incluye lista de las entidades relaciones.
 */

@Getter
@Setter
@Builder
public class PostRequestDTO {

    // Nombre del cargo
    @Size(min = 1, max = 100, message = "El nombre del cargo debe tener entre 1 y 100 caracteres")
    @NotBlank(message = "El nombre del cargo no puede estar en blanco")
    private String post_name;

    // Descripción del cargo
    @Size(min = 1, max = 250, message = "La descripción debe tener entre 1 y 250 caracteres")
    @NotBlank(message = "La descripción no puede estar en blanco")
    private String description;

}
