package com.content.customer_service.dto.request;

import lombok.*;
import jakarta.validation.constraints.*;

/**
 * DTO de request para Contact - Usa UUIDs para referencias
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactRequestDTO {

    @NotBlank(message = "El número de teléfono es obligatorio")
    @Size(max = 20, message = "El teléfono no puede exceder 20 caracteres")
    private String phoneNumber;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe tener formato válido")
    @Size(max = 100, message = "El email no puede exceder 100 caracteres")
    private String email;

    @NotBlank(message = "El cliente es obligatorio")
    private String clientUuid;

    @NotBlank(message = "El estado es obligatorio")
    private String stateEntityUuid;

    // Métodos adicionales para compatibilidad con servicios legacy
    public String getClient_id() {
        return clientUuid;
    }

    public String getPhone_number() {
        return phoneNumber;
    }
}
