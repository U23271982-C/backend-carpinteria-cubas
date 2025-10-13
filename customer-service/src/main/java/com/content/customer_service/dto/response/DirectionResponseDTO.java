package com.content.customer_service.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO de respuesta para Dirección.
 */
@Getter
@Setter
@Builder
public class DirectionResponseDTO {

    private Integer direction_id;
    private Integer client_id;
    private String client_name;
    private Integer direction_type_id;
    private String direction_type_description;
    private String direction_name;
    private String direction_number;
    private String reference;
    private Integer district_id;
    private String district_name;
    private Integer province_id;
    private String province_name;
    private Integer department_id;
    private String department_name;
    private Integer state_entity_id;
    private String state_entity_name;

}

