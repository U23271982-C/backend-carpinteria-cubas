package com.content.customer_service.dto.request;

import com.content.customer_service.util.ValidatorGroups;
import lombok.*;
import jakarta.validation.constraints.*;

import java.util.UUID;

/**
 * DTO de request para Direction - Usa UUIDs para referencias
 */
@Data
public class DirectionRequestDTO {

    @NotBlank(message = "El cliente es obligatorio",groups = ValidatorGroups.Create.class)
    private UUID client_uuid;

    @NotBlank(message = "El tipo de dirección es obligatorio", groups = ValidatorGroups.Create.class)
    private UUID direction_type_uuid;

    @NotBlank(message = "La direccion es obligatoria", groups = ValidatorGroups.Create.class)
    @Size(max = 100, message = "La dirección no puede exceder 100 caracteres" , groups = ValidatorGroups.Update.class)
    private String direction_name;

    @NotBlank(message = "El numero de la direccion es obligatoria", groups = ValidatorGroups.Create.class)
    @Size(max = 6, message = "El numero de la direccion no puede exceder 6 caracteres", groups = ValidatorGroups.Update.class)
    @Pattern(regexp = "\\d{6}", message = "El numero de la direccion debe contener solo números", groups = {ValidatorGroups.Create.class, ValidatorGroups.Update.class})
    private String direction_number;

    @Size(max = 255, message = "La referencia no puede exceder los 255 caracteres")
    private String reference;

    @NotBlank(message = "El distrito es obligatorio" ,groups = {ValidatorGroups.Create.class, ValidatorGroups.Update.class})
    private UUID district_uuid;

    private UUID state_entity_uuid;

}
