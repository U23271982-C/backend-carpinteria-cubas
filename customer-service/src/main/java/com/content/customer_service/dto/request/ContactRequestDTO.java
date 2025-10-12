package com.content.customer_service.dto.request;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO para la solicitud de creación de un Contacto.
 */

@Getter
@Setter
@Builder
public class ContactRequestDTO {

    // ID del Cliente asociado
    @Positive(message = "El ID del Cliente debe ser un número positivo")
    @NotNull(message = "El ID del Cliente no debe ser nulo")
    private Integer client_id;

    // Número de teléfono
    @NotBlank(message = "El número de teléfono no debe estar vacío")
    @Size(max = 20, message = "El número de teléfono no debe exceder los 20 caracteres")
    @Pattern(regexp = "^\\+?[0-9]{7,15}$", message = "El teléfono debe tener un formato válido")
    private String phone_number;

    // Email
    @NotBlank(message = "El email no debe estar vacío")
    @Email(message = "El email debe tener un formato válido")
    @Size(max = 100, message = "El email no debe exceder los 100 caracteres")
    private String email;

}
