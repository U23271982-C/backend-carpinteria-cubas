package com.content.customer_service.dto.request;

import lombok.*;
import jakarta.validation.constraints.*;

/**
 * DTO de request para Identification - Usa UUIDs para referencias
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdentificationRequestDTO {

    @NotBlank(message = "El número de identificación es obligatorio")
    @Size(max = 20, message = "El número de identificación no puede exceder 20 caracteres")
    private String identificationNumber;

    @NotBlank(message = "El tipo de identificación es obligatorio")
    private String identificationTypeUuid;

    @NotBlank(message = "El tipo de persona es obligatorio")
    private String personTypeUuid;

    @NotBlank(message = "El estado es obligatorio")
    private String stateEntityUuid;
}
