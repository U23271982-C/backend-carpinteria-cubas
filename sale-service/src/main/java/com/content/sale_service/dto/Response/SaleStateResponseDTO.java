package com.content.sale_service.dto.Response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SaleStateResponseDTO {
    private String name_state_current;
    private String description_state_current;
}
