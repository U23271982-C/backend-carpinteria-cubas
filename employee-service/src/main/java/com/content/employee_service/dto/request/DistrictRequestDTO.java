package com.content.employee_service.dto.request;

import com.content.employee_service.utility.ValidateGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

/**
 * Data Transfer Object para la entidad District.
 * Este DTO incluye lista de las entidades relaciones.
 */

@Getter
@Setter
@Builder
public class DistrictRequestDTO {

    // Nombre del distrito
    @NotBlank(message = "El nombre del distrito no puede estar vacío", groups = ValidateGroup.OnCreate.class)
    @Size(min = 1, max = 100, message = "El nombre del distrito debe tener entre 1 y 100 caracteres", groups = {ValidateGroup.OnCreate.class, ValidateGroup.OnUpdate.class})
    private String district_name;

    private UUID state_entity_uuid;

}
