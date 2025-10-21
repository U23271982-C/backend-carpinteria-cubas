package com.content.customer_service.dto.request;

import com.content.customer_service.util.ValidatorGroups;
import lombok.*;
import jakarta.validation.constraints.*;

import java.util.UUID;

/**
 * DTO de request para Identification - Usa UUIDs para referencias
 */
@Data
public class IdentificationRequestDTO {

    @NotBlank(message = "El número de identificación es obligatorio", groups = ValidatorGroups.Create.class)
    @Size(max = 11, message = "El número de identificación no puede exceder 11 caracteres", groups = ValidatorGroups.Update.class)
    @Pattern(regexp = "\\d{11}", message = "El numero de identificación debe contener solo números", groups = {ValidatorGroups.Create.class, ValidatorGroups.Update.class})
    private String identification_number;

    @NotBlank(message = "El tipo de identificación es obligatorio", groups = ValidatorGroups.Create.class)
    private UUID identification_type_uuid;

    private UUID state_entity_uuid;
}
