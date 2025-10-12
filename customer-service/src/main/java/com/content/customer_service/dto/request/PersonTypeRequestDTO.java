package com.content.customer_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object para la entidad Tipo de Persona.
 * Contiene los atributos necesarios para crear un Tipo de Persona.
 */

@Getter
@Setter
@Builder
public class PersonTypeRequestDTO {

    // Nombre del tipo de persona
    @NotBlank(message = "El nombre del tipo de persona no puede estar vacío")
    @Size(min = 3, max = 50, message = "El nombre del tipo de persona debe tener entre 3 y 50 caracteres")
    private String person_type_name;

}
