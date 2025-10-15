package com.content.customer_service.dto.request;

import lombok.*;
import jakarta.validation.constraints.*;

/**
 * DTO de request para crear/actualizar Cliente - Recibe UUIDs de referencias, nunca IDs internos
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequestDTO {

    @NotBlank(message = "El nombre del cliente es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    private String clientName;

    @NotBlank(message = "El apellido del cliente es obligatorio")
    @Size(max = 100, message = "El apellido no puede exceder 100 caracteres")
    private String clientLastName;

    // Referencias usando UUIDs - NUNCA recibimos IDs internos
    @NotBlank(message = "El tipo de cliente es obligatorio")
    private String clientTypeUuid;

    @NotBlank(message = "La identificación es obligatoria")
    private String identificationUuid;

    @NotBlank(message = "El estado es obligatorio")
    private String stateEntityUuid;
}
