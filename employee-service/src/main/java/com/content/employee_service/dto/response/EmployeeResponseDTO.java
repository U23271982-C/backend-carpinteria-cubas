package com.content.employee_service.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Builder
@Data
public class EmployeeResponseDTO {
    private String employee_name;
    private String employee_last_name;
    private LocalDateTime birth_date;
    private LocalDateTime register_date;
    private String identification_number;
    private String direction_email;
    private int identification_type_id;
    private int post_id;
    private int contract_id;
    private int distric_id;
}
