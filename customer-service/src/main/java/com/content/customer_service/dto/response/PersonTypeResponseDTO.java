package com.content.customer_service.dto.response;

import lombok.Data;
import java.util.UUID;

/**
 * DTO de respuesta para Tipo de Persona.
 */
@Data
public class PersonTypeResponseDTO {

    private UUID person_type_uuid;
    private String person_type_name;
    private String state_entity_name;

}

