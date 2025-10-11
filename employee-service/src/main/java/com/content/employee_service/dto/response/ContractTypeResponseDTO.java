package com.content.employee_service.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ContractTypeResponseDTO {
    private String person_type_name;
    private String contract_type_name;
}
