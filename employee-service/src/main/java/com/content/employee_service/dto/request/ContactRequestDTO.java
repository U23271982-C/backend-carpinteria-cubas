package com.content.employee_service.dto.request;

import com.content.employee_service.utility.ValidateGroup;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.UUID;

/**
 * Data Transfer Object para la entidad Contact.
 * Este DTO no incluye lista de las entidades relaciones.
 */

@Getter
@Setter
@Builder
public class ContactRequestDTO {

    // UUID del empleado asociado al contacto
    @NotNull(message = "El UUID del empleado no puede ser nulo", groups = ValidateGroup.OnCreate.class)
    private UUID employee_id_uuid;

    // Número de teléfono del contacto
    @NotBlank(message = "El número de teléfono no puede estar vacío", groups = ValidateGroup.OnCreate.class)
    @Size(min = 9, max = 9, message = "El número de teléfono debe tener exactamente 9 dígitos", groups = {ValidateGroup.OnCreate.class, ValidateGroup.OnUpdate.class})
    private String phone_number;

    // Correo electrónico del contacto
    @NotBlank(message = "El correo electrónico no puede estar vacío", groups = ValidateGroup.OnCreate.class)
    @Size(max = 100, message = "El correo electrónico no puede exceder los 100 caracteres", groups = {ValidateGroup.OnCreate.class, ValidateGroup.OnUpdate.class})
    @Email(message = "El correo electrónico debe tener un formato válido", groups = {ValidateGroup.OnCreate.class, ValidateGroup.OnUpdate.class})
    private String email;

    private UUID state_entity_uuid;
}
