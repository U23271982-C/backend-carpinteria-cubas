package com.content.employee_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * Data Transfer Object para la entidad ContractType.
 * Este DTO incluye lista de las entidades relaciones.
 */

@Getter
@Setter
@Builder
public class ContractTypeRequestDTO {

    // Nombre del tipo de contrato
    @NotBlank(message = "El nombre del tipo de contrato no puede estar vacío")
    @Size(min = 1, max = 100, message = "El nombre del tipo de contrato debe tener entre 1 y 100 caracteres")
    private String contract_type_name;

    @NotBlank(message = "La descripción del tipo de contrato no puede estar ser vacío")
    @Size(min = 1, max = 250, message = "La descripción del tipo de contrato no puede pasar los 250 caracteres")
    private String description;
}
