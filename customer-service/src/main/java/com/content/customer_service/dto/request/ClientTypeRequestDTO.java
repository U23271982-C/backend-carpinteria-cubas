package com.content.customer_service.dto.request;

import com.content.customer_service.util.ValidatorGroups;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * DTO para la solicitud de creación/actualización de un Tipo de Cliente.
 * Solo incluye los campos que deben ser proporcionados manualmente desde el frontend.
 */

@Data
public class ClientTypeRequestDTO {

    // Nombre del tipo de cliente
    @NotBlank(message = "El nombre del tipo de cliente no debe estar vacío", groups = ValidatorGroups.Create.class)
    @Size(max = 50, message = "El nombre del tipo de cliente no debe exceder los 50 caracteres", groups = ValidatorGroups.Update.class)
    private String client_type_name;

    // Descripción del tipo de cliente
    @NotBlank(message = "La descripción no debe estar vacía", groups = ValidatorGroups.Create.class)
    @Size(max = 100, message = "La descripción no debe exceder los 100 caracteres", groups = ValidatorGroups.Update.class)
    private String description;

    private UUID state_entity_uuid;

}
