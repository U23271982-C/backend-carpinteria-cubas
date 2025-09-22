package com.content.sale_service.dto.builder;

import com.content.sale_service.dto.Request.SaleRequestDTO;
import com.content.sale_service.model.Sale;
import com.content.sale_service.model.SaleDetail;
import com.content.sale_service.model.SaleState;
import com.content.sale_service.model.StateEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class BuildDTO {
    /**
     * Construye una entidad Sale a partir del DTO
     */
    public static Sale buildSaleFromDTO(SaleRequestDTO dto) {
        Sale sale = Sale.builder()
                .number_sale(generateSaleNumber(dto.getClient_id()))
                .date_sale(LocalDate.now())
                .hour_sale(LocalTime.now())
                .client_id(dto.getClient_id())
                .build();

        // Calcular totales
        double subtotal = dto.getSale_details().stream()
                .mapToDouble(SaleDetail::getSubtotal)
                .sum();
        double total = dto.getSale_details().stream()
                .mapToDouble(SaleDetail::getTotal)
                .sum();

        sale.setSubtotal(subtotal);
        sale.setTotal(total);

        // Establecer estado por defecto (PAID)
        SaleState defaultState = SaleState.builder().id(1).build();
        sale.setSale_state(defaultState);

        // Establecer estado por defecto (activo)
        StateEntity defaultEntity = StateEntity.builder().id(1).build();
        sale.setState_entity(defaultEntity);

        // Asociar detalles a la venta
        dto.getSale_details().forEach(detail -> detail.setSale(sale));
        sale.setSale_detail(dto.getSale_details());

        return sale;
    }
    /**
     * Genera un número único para la venta
     */
    private static String generateSaleNumber(int clientId) {
        String timestamp = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String timeComponent = LocalTime.now().format(DateTimeFormatter.ofPattern("HHmmss"));
        return String.format("S-%d-%s-%s", clientId, timestamp, timeComponent); //S-12-20250920-190912
    }
}
