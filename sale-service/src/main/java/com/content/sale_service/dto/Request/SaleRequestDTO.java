package com.content.sale_service.dto.Request;

import com.content.sale_service.model.SaleDetail;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

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
    @Positive(message = "El id del cliente no puede ser negativo")
    @NotNull(message = "El cliente no puede ser nulo")
    private int client_id;
    //private int state_sale_current_id;
    //private int state_entity_current_id; //
    @NotNull(message = "La lista de detalles de venta no puede ser nula")
    @Size(min = 1, message = "La lista de detalles de venta debe contener al menos un elemento")
    private List<@Valid SaleDetailRequestDTO> sale_details; // Lista de detalles de la venta, para poder sacar el total y el subtotal
}
