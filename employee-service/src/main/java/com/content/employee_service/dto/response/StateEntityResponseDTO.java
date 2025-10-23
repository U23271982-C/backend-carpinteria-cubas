package com.content.employee_service.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;
@Data
@Builder
public class StateEntityResponseDTO {
    private UUID uuid;
    private String state_name;
}
