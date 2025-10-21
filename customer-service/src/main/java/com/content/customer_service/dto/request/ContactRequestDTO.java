package com.content.customer_service.dto.request;

import com.content.customer_service.util.ValidatorGroups;
import lombok.*;
import jakarta.validation.constraints.*;

import java.util.UUID;

/**
 * DTO de request para Contact - Usa UUIDs para referencias
 */
@Data
public class ContactRequestDTO {

    @NotBlank(message = "El cliente es obligatorio", groups = ValidatorGroups.Create.class)
    private UUID client_uuid;

    @NotBlank(message = "El número de teléfono es obligatorio", groups = ValidatorGroups.Create.class)
    @Size(max = 12, message = "El teléfono no puede exceder 12 caracteres", groups = ValidatorGroups.Update.class)
    @Pattern(regexp = "\\d{12}", message = "El numero de telefono debe contener solo números", groups = {ValidatorGroups.Create.class, ValidatorGroups.Update.class})
    private String phone_number;

    @NotBlank(message = "El email es obligatorio", groups = ValidatorGroups.Create.class)
    @Email(message = "El email debe tener formato válido", groups = {ValidatorGroups.Create.class, ValidatorGroups.Update.class})
    @Size(max = 100, message = "El email no puede exceder 100 caracteres", groups = ValidatorGroups.Update.class)
    private String email;

    private UUID state_entity_uuid;

}
