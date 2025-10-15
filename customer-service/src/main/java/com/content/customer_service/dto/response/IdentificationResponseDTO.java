package com.content.customer_service.dto.response;

import lombok.*;

/**
 * DTO de respuesta para Identification - Solo expone UUIDs
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdentificationResponseDTO {

    private String uuid;
    private String identificationNumber;

    private String identificationTypeUuid;
    private String identificationTypeName;

    private String personTypeUuid;
    private String personTypeName;

    private String stateEntityUuid;
    private String stateName;
}
