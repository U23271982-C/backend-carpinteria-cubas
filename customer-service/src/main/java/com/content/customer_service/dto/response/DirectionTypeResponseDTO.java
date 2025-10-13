package com.content.customer_service.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO de respuesta para Tipo de Dirección.
 */
@Getter
@Setter
@Builder
public class DirectionTypeResponseDTO {

    private Integer direction_type_id;
    private String description;
    private Integer state_entity_id;
    private String state_entity_name;

}

