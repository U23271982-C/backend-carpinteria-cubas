package com.contenet.product_service.dto.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrefabricatedProductResponseDTO {
    private int prefabricatedProductId;
    private String prefabricatedProductName;
    private String codSku;
    private String pathImage;
    private BigDecimal salePrice;
    private int availableStock;
    private int minStock;
    private LocalDateTime lastProductionDate;
    private String specification;
    private ProductBaseResponseDTO productBase;
    private StateEntityresponseDTO stateEntity;
}
