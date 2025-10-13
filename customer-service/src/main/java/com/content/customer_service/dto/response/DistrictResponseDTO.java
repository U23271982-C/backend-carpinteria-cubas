package com.content.customer_service.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO de respuesta para Distrito.
 */
@Getter
@Setter
@Builder
public class DistrictResponseDTO {

    private Integer district_id;
    private String district_name;
    private Integer province_id;
    private String province_name;
    private Integer state_entity_id;
    private String state_entity_name;

}

