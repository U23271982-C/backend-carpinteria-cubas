package com.content.employee_service.dto.request;

import com.content.employee_service.utility.ValidateGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

/**
 * Data Transfer Object para la entidad Position.
 * Este DTO incluye lista de las entidades relaciones.
 */

@Getter
@Setter
@Builder
public class PostRequestDTO {

    // Nombre del cargo
    @Size(min = 1, max = 100, message = "El nombre del cargo debe tener entre 1 y 100 caracteres", groups = {ValidateGroup.OnCreate.class, ValidateGroup.OnUpdate.class})
    @NotBlank(message = "El nombre del cargo no puede estar en blanco", groups = ValidateGroup.OnCreate.class)
    private String post_name;

    // Descripción del cargo
    @Size(min = 1, max = 250, message = "La descripción debe tener entre 1 y 250 caracteres", groups = {ValidateGroup.OnCreate.class, ValidateGroup.OnUpdate.class})
    @NotBlank(message = "La descripción no puede estar en blanco", groups = ValidateGroup.OnCreate.class)
    private String description;

    private UUID state_entity_uuid;
}
