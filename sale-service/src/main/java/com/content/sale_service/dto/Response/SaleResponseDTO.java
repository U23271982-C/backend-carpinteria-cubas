package com.content.sale_service.dto.Response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
public class SaleResponseDTO {
    private String number_sale;
    private LocalDate date_sale;
    private LocalTime hour_sale;
    private double subtotal;
    private double total;
    private int client_id;
    private String id_state_sale_current;
}
