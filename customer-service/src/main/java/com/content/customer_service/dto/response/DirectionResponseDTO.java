package com.content.customer_service.dto.response;

import lombok.*;
import java.util.UUID;

/**
 * DTO de respuesta para Direction - Solo expone UUIDs, nunca IDs internos
 */
@Data
public class DirectionResponseDTO {

    private UUID direction_uuid;
    private UUID client_uuid;
    private UUID direction_type_uuid;
    private String direction_name;
    private String direction_number;
    private String reference;
    private UUID district_uuid;
    private String state_entity_name;

}
