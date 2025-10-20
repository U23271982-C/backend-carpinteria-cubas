package com.content.customer_service.dto.request;

import com.content.customer_service.util.ValidatorGroups;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * DTO para la solicitud de creación/actualización de un Departamento.
 * Solo incluye los campos que deben ser proporcionados manualmente desde el frontend.
 * Nota: El departamento se crea automáticamente si no existe al registrar una dirección.
 */

@Getter
@Setter
@Builder
public class DepartmentRequestDTO {

    // Nombre del departamento
    @NotBlank(groups = ValidatorGroups.Create.class, message = "El nombre del departamento es obligatorio")
    @Size(min = 1 , max = 100, message = "El nombre no puede exceder 100 caracteres", groups = ValidatorGroups.Update.class)
    private String department_name;

    private UUID state_entity_uuid;

}
