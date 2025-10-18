package com.content.employee_service.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class ContactResponseDTO {
    private UUID uuid;
    private UUID employee_id_uuid;
    private String phone_number;
    private String email;
    private String state_entity;
}
