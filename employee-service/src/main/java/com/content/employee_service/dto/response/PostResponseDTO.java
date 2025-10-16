package com.content.employee_service.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class PostResponseDTO {
    private UUID uuid;
    private String post_name;
    private String description;
    private String state_entity;
}
