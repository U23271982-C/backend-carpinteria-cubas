package com.contenet.product_service.dto.response;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategoryResponseDTO {
    private int id;
    private String productCategoryName;
    private StateEntityresponseDTO stateEntity;
    private List<ProductBaseResponseDTO> productBases;
}
