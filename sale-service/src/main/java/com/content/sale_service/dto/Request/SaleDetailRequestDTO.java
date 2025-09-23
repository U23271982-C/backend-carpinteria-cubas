package com.content.sale_service.dto.Request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SaleDetailRequestDTO {
    private int id_sale;
    private int id_product;
    private int quantity;
}
