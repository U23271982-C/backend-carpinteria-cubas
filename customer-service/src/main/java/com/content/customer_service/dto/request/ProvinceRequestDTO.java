package com.content.customer_service.dto.request;

import com.content.customer_service.util.ValidatorGroups;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * DTO para la solicitud de creación/actualización de una Provincia.
 * Solo incluye los campos que deben ser proporcionados manualmente desde el frontend.
 * Nota: La provincia se crea automáticamente si no existe al registrar una dirección.
 */

@Data
public class ProvinceRequestDTO {

    // Nombre de la provincia
    @NotBlank(groups = ValidatorGroups.Create.class, message = "El nombre de la provincia es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres", groups = ValidatorGroups.Update.class)
    private String province_name;

    // UUID del departamento asociado
    @NotNull(groups = ValidatorGroups.Create.class, message = "El UUID del departamento es obligatorio")
    private UUID department_UUID;

    private UUID state_entity_uuid;

}
