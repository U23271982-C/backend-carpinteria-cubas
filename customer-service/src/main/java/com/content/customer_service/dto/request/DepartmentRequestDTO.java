package com.content.customer_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO para la solicitud de creación/actualización de un Departamento.
 * Solo incluye los campos que deben ser proporcionados manualmente desde el frontend.
 * Nota: El departamento se crea automáticamente si no existe al registrar una dirección.
 */

@Getter
@Setter
@Builder
public class DepartmentRequestDTO {

    // Nombre del departamento (ejemplo: "Lima", "Arequipa", "Cusco")
    @NotBlank(message = "El nombre del departamento no debe estar vacío")
    @Size(max = 100, message = "El nombre del departamento no debe exceder los 100 caracteres")
    private String department_name;

}
