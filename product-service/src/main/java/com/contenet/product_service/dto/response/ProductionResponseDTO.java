package com.contenet.product_service.dto.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductionResponseDTO {
    private int productionId;
    private int saleId;
    private CustomizedProductResponseDTO customizedProduct;
    private ProductionStateResponseDTO productionState;
    private StateEntityresponseDTO stateEntity;
}
