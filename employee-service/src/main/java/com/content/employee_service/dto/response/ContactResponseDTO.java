package com.content.employee_service.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ContactResponseDTO {
    private int employee_id;
    private String phone_number;
    private String email;
}
