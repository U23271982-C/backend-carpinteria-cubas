package com.content.customer_service.dto.request;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO para la solicitud de creación/actualización de un Contacto.
 * Solo incluye los campos que deben ser proporcionados manualmente desde el frontend.
 */
@Getter
@Setter
@Builder
public class ContactRequestDTO {

    // ID del cliente asociado
    @Positive(message = "El ID del cliente debe ser un número positivo")
    @NotNull(message = "El ID del cliente no debe ser nulo")
    private Integer client_id;

    // Número de teléfono del contacto
    @NotBlank(message = "El número de teléfono no debe estar vacío")
    @Size(max = 20, message = "El número de teléfono no debe exceder los 20 caracteres")
    @Pattern(regexp = "^[0-9+\\-\\s()]*$", message = "El número de teléfono solo puede contener números y caracteres +, -, (, ), espacios")
    private String phone_number;

    // Correo electrónico del contacto
    @NotBlank(message = "El correo electrónico no debe estar vacío")
    @Email(message = "Debe proporcionar un correo electrónico válido")
    @Size(max = 100, message = "El correo electrónico no debe exceder los 100 caracteres")
    private String email;

}
