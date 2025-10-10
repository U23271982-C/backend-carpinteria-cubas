package com.content.employee_service.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

/**
 * Data Transfer Object para la entidad Contact.
 * Este DTO no incluye lista de las entidades relaciones.
 */

@Getter
@Setter
@Builder
public class ContactRequestDTO {

    // ID del empleado asociado al contacto
    @Positive(message = "El ID del empleado debe ser un número positivo")
    @NotNull(message = "El ID del empleado no puede ser nulo")
    private Integer employee_id;

    // Número de teléfono del contacto
    @NotBlank(message = "El número de teléfono no puede estar vacío")
    @Size(min = 9, max = 9, message = "El número de teléfono debe tener exactamente 9 dígitos")
    private String phone_number;

    // Correo electrónico del contacto
    @NotBlank(message = "El correo electrónico no puede estar vacío")
    @Size(max = 100, message = "El correo electrónico no puede exceder los 100 caracteres")
    @Email(message = "El correo electrónico debe tener un formato válido")
    private String email;

}
