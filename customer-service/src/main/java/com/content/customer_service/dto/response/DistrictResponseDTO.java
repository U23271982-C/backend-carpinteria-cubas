package com.content.customer_service.dto.response;

import lombok.Data;
import java.util.UUID;

/**
 * DTO de respuesta para Distrito.
 */
@Data
public class DistrictResponseDTO {

    private UUID district_uuid;
    private String district_name;
    private UUID province_uuid;
    private String state_entity_name;

}

