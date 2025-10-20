package com.contenet.product_service.dto.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductionStateResponseDTO {
    private int productionStateId;
    private String productionStateName;
    private StateEntityresponseDTO stateEntity;
}
