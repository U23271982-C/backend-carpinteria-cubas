package com.content.customer_service.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO de respuesta para Tipo de Identificación.
 */
@Getter
@Setter
@Builder
public class IdentificationTypeResponseDTO {

    private Integer identification_type_id;
    private String identification_type_name;
    private Integer person_type_id;
    private String person_type_name;
    private Integer state_entity_id;
    private String state_entity_name;

}

