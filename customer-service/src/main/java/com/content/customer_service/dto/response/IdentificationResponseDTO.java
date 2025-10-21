package com.content.customer_service.dto.response;

import lombok.*;
import java.util.UUID;

/**
 * DTO de respuesta para Identification - Solo expone UUIDs
 */
@Data
public class IdentificationResponseDTO {

    private UUID identification_uuid;
    private String identification_number;
    private UUID identification_type_uuid;
    private String state_entity_name;

}
