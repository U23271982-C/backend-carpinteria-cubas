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
 * DTO para la solicitud de creación/actualización de un Tipo de Persona.
 * Solo incluye los campos que deben ser proporcionados manualmente desde el frontend.
 */

@Data
public class PersonTypeRequestDTO {

    // Nombre del tipo de persona (ejemplo: "Natural", "Jurídica")
    @NotBlank(message = "El nombre del tipo de persona no debe estar vacío", groups = ValidatorGroups.Create.class)
    @Size(max = 100, message = "El nombre del tipo de persona no debe exceder los 100 caracteres", groups = ValidatorGroups.Update.class)
    private String persona_type_name;

    private UUID state_entity_uuid;

}
