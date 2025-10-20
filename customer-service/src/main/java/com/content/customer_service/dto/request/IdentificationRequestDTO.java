package com.content.customer_service.dto.request;

import com.content.customer_service.util.ValidatorGroups;
import lombok.*;
import jakarta.validation.constraints.*;

import java.util.UUID;

/**
 * DTO de request para Identification - Usa UUIDs para referencias
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdentificationRequestDTO {

    @NotBlank(message = "El número de identificación es obligatorio", groups = ValidatorGroups.Create.class)
    @Size(max = 11, message = "El número de identificación no puede exceder 11 caracteres", groups = ValidatorGroups.Update.class)
    private String identificationNumber;

    @NotBlank(message = "El tipo de identificación es obligatorio", groups = ValidatorGroups.Create.class)
    private UUID identification_type_uuid;

    private UUID stateEntityUuid;
}
