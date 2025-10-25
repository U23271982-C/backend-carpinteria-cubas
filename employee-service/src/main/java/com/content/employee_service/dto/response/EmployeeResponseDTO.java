package com.content.employee_service.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
public class EmployeeResponseDTO {
    private UUID uuid;
    private String employee_name;
    private String employee_last_name;
    private LocalDateTime birth_date;
    private LocalDateTime register_date;
    private String identification_number;
    private String direction_name;
    private String direction_email;
    private UUID identification_type_uuid;
    private UUID post_uuid;
    private UUID contract_uuid;
    private UUID distric_uuid;
    private String state_entity;
}
