package com.content.sale_service.dto.Request;

import java.time.LocalDate;

/**
 * Peticione de entrada del cliente.
 * Crear o Actualizar una venta
 */
public class SaleRequestDTO {
    // Codigo de la venta, para identificar la venta se realiza en lógica bd
    private LocalDate date_sale;

}
