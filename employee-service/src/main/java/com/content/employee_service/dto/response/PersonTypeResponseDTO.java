package com.content.employee_service.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class PersonTypeResponseDTO {
    private UUID uuid;
    private String person_type_name;
    private String state_entity;
}
