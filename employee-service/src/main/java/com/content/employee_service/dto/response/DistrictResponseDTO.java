package com.content.employee_service.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class DistrictResponseDTO {
    private UUID uuid;
    private String district_name;
    private String state_entity;
}
