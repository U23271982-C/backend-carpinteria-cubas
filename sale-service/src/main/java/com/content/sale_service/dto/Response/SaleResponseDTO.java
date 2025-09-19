package com.content.sale_service.dto.Response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SaleResponseDTO {
    private String number_sale;
    private String date_sale;
    private String state;
    private int id_client;
    private int total;
    private int subtotal;
}
