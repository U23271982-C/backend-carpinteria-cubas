package com.content.sale_service.dto.Request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SaleDetailRequestDTO {
    @NotNull(message = "El id de la venta no puede ser nulo")
    @Positive(message = "El id de la venta no puede ser negativo")
    private int id_sale;
    @NotNull(message = "El id del producto no puede ser nulo")
    @Positive(message = "El id del producto no puede ser negativo")
    private int id_product;
    @NotNull(message = "La cantidad de productos no puede ser nulo")
    @Positive(message = "La cantidad de productos no puede ser negativo")
    private int quantity;
}
