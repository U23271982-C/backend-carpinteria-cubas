package com.contenet.product_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductBaseResponseDTO {
    private int productBaseId;
    private String productBaseName;
    private String description;
    private BigDecimal priceBase;
    private LocalDateTime registerDate;
    private ProductCategoryResponseDTO productCategory;
    private StateEntityresponseDTO stateEntity;
    private List<PrefabricatedProductResponseDTO> prefabricatedProducts;
    private List<CustomizedProductResponseDTO> customizedProducts;
}
