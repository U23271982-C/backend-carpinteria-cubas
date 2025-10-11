package com.content.employee_service.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PersonTypeResponseDTO {
    private String person_type_name;
}
