package com.content.employee_service.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
public class ContractResponseDTO {
    private UUID uuid;
    private String contract_name;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private int contract_type_id;
    private double salary;
}
