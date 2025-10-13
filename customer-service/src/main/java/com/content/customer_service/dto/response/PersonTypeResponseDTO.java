package com.content.customer_service.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO de respuesta para Tipo de Persona.
 */
@Getter
@Setter
@Builder
public class PersonTypeResponseDTO {

    private Integer person_type_id;
    private String persona_type_name;
    private Integer state_entity_id;
    private String state_entity_name;

}

