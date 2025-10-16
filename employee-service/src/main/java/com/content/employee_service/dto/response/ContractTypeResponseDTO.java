package com.content.employee_service.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class ContractTypeResponseDTO {
    private UUID uuid;
    private String contract_type_name;
    private String description;
}
