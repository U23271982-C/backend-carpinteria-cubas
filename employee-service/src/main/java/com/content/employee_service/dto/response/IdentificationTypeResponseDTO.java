package com.content.employee_service.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class IdentificationTypeResponseDTO {
    private String identification_type_name;
    private int person_type_id;
}
