package com.content.sale_service.dto.Request;

import com.content.sale_service.model.SaleDetail;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Peticione de entrada del cliente.
 * Crear o Actualizar una venta
 */
@Getter
@Setter
@Builder
public class SaleRequestDTO {
    // Codigo de la venta, para identificar la venta se realiza en lógica bd
    //private double subtotal;
    //private double total;
    private int client_id;
    //private int state_sale_current_id;
    //private int state_entity_current_id; //
    private List<SaleDetail> sale_details; // Lista de detalles de la venta, para poder sacar el total y el subtotal
}
