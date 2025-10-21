package com.content.customer_service.dto.response;

import lombok.Data;
import java.util.UUID;

/**
 * DTO de respuesta para Tipo de Dirección.
 */
@Data
public class DirectionTypeResponseDTO {

    private UUID direction_type_uuid;
    private String direction_type_name;
    private String description;
    private String state_entity_name;

}

