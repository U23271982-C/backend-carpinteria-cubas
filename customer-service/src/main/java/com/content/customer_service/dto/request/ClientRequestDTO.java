package com.content.customer_service.dto.request;

import com.content.customer_service.util.ValidatorGroups;
import lombok.*;
import jakarta.validation.constraints.*;

import java.util.UUID;

/**
 * DTO de request para crear/actualizar Cliente - Recibe UUIDs de referencias, nunca IDs internos
 */
@Data
public class ClientRequestDTO {

    @NotBlank(message = "El nombre del cliente es obligatorio", groups = ValidatorGroups.Create.class)
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    private String client_name;

    @NotBlank(message = "El apellido del cliente es obligatorio" , groups = ValidatorGroups.Create.class)
    @Size(max = 100, message = "El apellido no puede exceder 100 caracteres")
    private String client_last_name;

    @NotBlank(message = "El tipo de cliente es obligatorio", groups = {ValidatorGroups.Create.class, ValidatorGroups.Update.class})
    private UUID client_type_uuid;

    @NotBlank(message = "La identificación es obligatoria", groups = ValidatorGroups.Create.class)
    private UUID identification_uuid;

    private UUID state_entity_uuid;
}
