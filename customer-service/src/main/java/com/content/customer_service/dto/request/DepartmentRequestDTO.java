package com.content.customer_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO para la solicitud de creación de un Departamento.
 */

@Getter
@Setter
@Builder
public class DepartmentRequestDTO {

    // Nombre del departamento
    @NotBlank(message = "El nombre del departamento no debe estar vacío")
    @Size(min = 3, max = 50, message = "El nombre del departamento debe tener entre 3 y 50 caracteres")
    private String department_name;

}
