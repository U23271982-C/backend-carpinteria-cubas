package com.content.customer_service.dto.response;

import lombok.Data;
import java.util.UUID;

/**
 * DTO de respuesta para Tipo de Cliente.
 */
@Data
public class ClientTypeResponseDTO {

    private UUID client_type_uuid;
    private String client_type_name;
    private String description;
    private String state_entity_name;

}

