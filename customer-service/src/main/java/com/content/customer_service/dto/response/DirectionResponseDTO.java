package com.content.customer_service.dto.response;

import lombok.*;

/**
 * DTO de respuesta para Direction - Solo expone UUIDs, nunca IDs internos
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DirectionResponseDTO {

    private String uuid; // UUID público
    private String directionName;
    private String addressLine1;
    private String addressLine2;

    // Referencias usando UUIDs
    private String clientUuid;
    private String clientName;

    private String directionTypeUuid;
    private String directionTypeName;

    private String districtUuid;
    private String districtName;

    private String provinceUuid;
    private String provinceName;

    private String departmentUuid;
    private String departmentName;

    private String stateEntityUuid;
    private String stateName;

    // Métodos de compatibilidad para controladores legacy
    public String getDirection_id() {
        return uuid;
    }
}
