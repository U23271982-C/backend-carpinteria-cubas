package com.content.customer_service.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO de respuesta para Tipo de Cliente.
 */
@Getter
@Setter
@Builder
public class ClientTypeResponseDTO {

    private Integer client_type_id;
    private String client_type_name;
    private String description;
    private Integer state_entity_id;
    private String state_entity_name;

}

