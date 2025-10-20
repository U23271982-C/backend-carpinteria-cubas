package com.content.customer_service.dto.request;

import com.content.customer_service.util.ValidatorGroups;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * DTO para la solicitud de creación/actualización de un Distrito.
 * Solo incluye los campos que deben ser proporcionados manualmente desde el frontend.
 * Nota: El distrito se crea automáticamente si no existe al registrar una dirección.
 */

@Getter
@Setter
@Builder
public class DistrictRequestDTO {

    // Nombre del distrito
    @NotBlank(groups = ValidatorGroups.Create.class, message = "El nombre del distrito es obligatorio")
    @Size(max = 100, message = "El nombre del distrito no puede exceder 100 caracteres", groups = ValidatorGroups.Update.class)
    private String district_name;

    // ID de la provincia asociada
    @NotNull(message = "El UUID de la provincia no debe ser nulo", groups = ValidatorGroups.Create.class)
    private UUID province_uuid;

    private UUID state_entity_uuid;
}
