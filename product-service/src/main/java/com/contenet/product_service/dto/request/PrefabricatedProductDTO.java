package com.contenet.product_service.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;


import java.math.BigDecimal;

@Data
@Getter
@Setter

public class PrefabricatedProductDTO {
    @NotNull(message = "El ID del producto base no puede ser nulo.")
    @Positive(message = "El ID del producto base debe ser un número positivo.")
    private Integer product_base_id;

    @NotBlank(message = "El nombre del producto prefabricado no puede estar vacío.")
    @Size(max = 255, message = "El nombre no puede exceder los 255 caracteres.")
    private String prefabricatedProductName;

    @NotNull(message = "El SKU no puede ser nulo.")
    private Integer codSku;

    @Size(max = 255)
    private String pathImage;

    @NotNull(message = "El precio de venta no puede ser nulo.")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio de venta debe ser mayor que cero.")
    private BigDecimal salePrice;

    @NotNull(message = "El stock disponible no puede ser nulo.")
    @Min(value = 0, message = "El stock disponible no puede ser negativo.")
    private Integer availableStock;

    @NotNull(message = "El stock mínimo no puede ser nulo.")
    @Min(value = 0, message = "El stock mínimo no puede ser negativo.")
    private Integer minStock;

    @Size(max = 255, message = "Las especificaciones no pueden exceder los 255 caracteres.")
    private String specifications;
}
