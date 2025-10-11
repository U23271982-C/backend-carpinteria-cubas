package com.content.employee_service.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PostResponseDTO {
    private String post_name;
    private int description;
}
