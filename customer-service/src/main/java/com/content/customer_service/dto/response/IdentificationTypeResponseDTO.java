package com.content.customer_service.dto.response;

import lombok.Data;
import java.util.UUID;

/**
 * DTO de respuesta para Tipo de Identificación.
 */
@Data
public class IdentificationTypeResponseDTO {

    private UUID identification_type_uuid;
    private String identification_type_name;
    private UUID person_type_uuid;
    private String state_entity_name;

}

