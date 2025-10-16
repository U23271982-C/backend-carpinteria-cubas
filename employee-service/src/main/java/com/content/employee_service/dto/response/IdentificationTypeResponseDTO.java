package com.content.employee_service.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class IdentificationTypeResponseDTO {
    private UUID uuid;
    private String identification_type_name;
    private UUID person_type_uuid;
    private String state_entity;
}
