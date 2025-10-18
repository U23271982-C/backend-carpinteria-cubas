package com.contenet.product_service.dto.request;

import lombok.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Getter
@Setter
public class ProductBaseDTO {

    @NotBlank(message = "El nombre del producto no puede estar vacío.")
    @Size(max = 255, message = "El nombre del producto no puede exceder los 255 caracteres.")
    private String productName;

    @Size(max = 255, message = "La descripción no puede exceder los 255 caracteres.")
    private String description;

    @NotNull(message = "El precio base no puede ser nulo.")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio base debe ser mayor que cero.")
    private BigDecimal priceBase;

    @NotNull(message = "El ID de la categoría no puede ser nulo.")
    @Positive(message = "El ID de la categoría debe ser un número positivo.")
    private Integer productCategoryId;
}
