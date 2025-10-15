package com.content.customer_service.dto.request;

import lombok.*;
import jakarta.validation.constraints.*;

/**
 * DTO de request para Direction - Usa UUIDs para referencias
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DirectionRequestDTO {

    private String directionName;

    @NotBlank(message = "La línea de dirección 1 es obligatoria")
    @Size(max = 255, message = "La dirección no puede exceder 255 caracteres")
    private String addressLine1;

    @Size(max = 255, message = "La dirección línea 2 no puede exceder 255 caracteres")
    private String addressLine2;

    @NotBlank(message = "El cliente es obligatorio")
    private String clientUuid;

    @NotBlank(message = "El tipo de dirección es obligatorio")
    private String directionTypeUuid;

    @NotBlank(message = "El distrito es obligatorio")
    private String districtUuid;

    @NotBlank(message = "El estado es obligatorio")
    private String stateEntityUuid;

    // Métodos adicionales para compatibilidad con servicios legacy
    public String getClient_id() {
        return clientUuid;
    }

    public String getDirection_type_id() {
        return directionTypeUuid;
    }

    public String getDirection_name() {
        return directionName;
    }

    public String getDirection_number() {
        return addressLine1;
    }

    public String getReference() {
        return addressLine2;
    }

    public String getDepartment_name() {
        // Este método será manejado por el servicio usando el districtUuid
        return null;
    }

    public String getProvince_name() {
        // Este método será manejado por el servicio usando el districtUuid
        return null;
    }

    public String getDistrict_name() {
        // Este método será manejado por el servicio usando el districtUuid
        return null;
    }
}
