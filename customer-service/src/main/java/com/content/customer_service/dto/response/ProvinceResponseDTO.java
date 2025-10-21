package com.content.customer_service.dto.response;

import lombok.Data;
import java.util.UUID;

/**
 * DTO de respuesta para Provincia.
 */
@Data
public class ProvinceResponseDTO {

    private UUID province_uuid;
    private String province_name;
    private UUID department_uuid;
    private String state_entity_name;

}

