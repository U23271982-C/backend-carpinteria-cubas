package com.contenet.product_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomizedProductResponseDTO {
    private int product_base_id;
    private String specification;
    private int stimatedTimeHours;
    private ProductBaseResponseDTO product_baseid;
    private StateEntityresponseDTO stateEntity;
    private List<ProductionResponseDTO> productions;
}
