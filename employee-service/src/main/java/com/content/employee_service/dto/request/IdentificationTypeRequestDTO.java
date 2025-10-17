package com.content.employee_service.dto.request;

import com.content.employee_service.utility.ValidateGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

/**
 * Data Transfer Object para la entidad IdentificationType.
 * Este DTO incluye lista de las entidades relaciones.
 */

@Getter
@Setter
@Builder
public class IdentificationTypeRequestDTO {

    // Nombre del tipo de identificación
    @Size(min = 1, max = 100, message = "El nombre del tipo de identificación debe tener entre 1 y 100 caracteres", groups = {ValidateGroup.OnCreate.class, ValidateGroup.OnUpdate.class})
    @NotBlank(message = "El nombre del tipo de identificación no puede estar vacío", groups = ValidateGroup.OnCreate.class)
    private String identification_type_name;

    // ID del Tipo de Persona
    @NotNull(message = "El id del tipo de persona no puede ser nulo", groups = ValidateGroup.OnCreate.class)
    private UUID person_type_uuid;

    private UUID state_entity_uuid;
}
