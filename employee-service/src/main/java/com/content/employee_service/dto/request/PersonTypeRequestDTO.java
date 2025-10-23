package com.content.employee_service.dto.request;

import com.content.employee_service.utility.ValidateGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

/**
 * Data Transfer Object para la entidad PersonType.
 * Este DTO incluye lista de las entidades relaciones.
 */

@Getter
@Setter
@Builder
public class PersonTypeRequestDTO {

    // Nombre del tipo de persona
    @Size(min = 1, max = 100, message = "El nombre del tipo de persona tiene que estar entre 1 a 100 caracteres", groups = {ValidateGroup.OnCreate.class, ValidateGroup.OnUpdate.class})
    @NotBlank(message = "El nombre del tipo de persona no puede estar en blanco", groups = ValidateGroup.OnCreate.class)
    private String person_type_name;

    private UUID state_entity_uuid;
}
