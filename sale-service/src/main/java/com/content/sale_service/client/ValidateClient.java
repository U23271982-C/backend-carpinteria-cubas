package com.content.sale_service.client;

import com.content.sale_service.dto.Request.SaleRequestDTO;
import com.content.sale_service.dto.external.ProductDTO;
import com.content.sale_service.execption.ExecptionValidation;
import com.content.sale_service.model.Sale;
import com.content.sale_service.model.SaleDetail;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
@Slf4j
@AllArgsConstructor
public class ValidateClient {
    private final CustomerClient customerClient;
    private final ProductClient productClient;



    /**
     * Valida que el cliente existe usando el Feign client
     */
    public void validateCustomerExists(int customerId) {
        try {
            Boolean customerExists = customerClient.customerExists(customerId);
            if (!customerExists) {
                log.error("Cliente no encontrado con ID: {}", customerId);
                throw new ExecptionValidation("Cliente no encontrado con ID: " + customerId);
            }
        } catch (Exception e) {
            log.error("Error al validar cliente con ID: {}", customerId, e);
            throw new ExecptionValidation("Error al validar cliente: " + e.getMessage());
        }
    }

    /**
     * Valida y procesa los detalles de la venta
     */
    public void validateAndProcessSaleDetails(List<SaleDetail> saleDetails) {
        if (saleDetails == null || saleDetails.isEmpty()) {
            throw new ExecptionValidation("La venta debe tener al menos un detalle");
        }

        for (SaleDetail detail : saleDetails) {
            // Validar que el producto existe y tiene stock
            validateProductStock(detail.getProduct_id(), detail.getQuantity());

            // Calcular subtotal y total del detalle
            ProductDTO product = productClient.getProductById(detail.getProduct_id());
            double unitPrice = product.getPrice().doubleValue();
            detail.setPrice_unit(unitPrice);
            detail.setSubtotal(unitPrice * detail.getQuantity());
            detail.setTotal(detail.getSubtotal()); // Asumiendo sin impuestos por ahora
        }
    }

    /**
     * Valida que el producto existe y tiene stock suficiente
     */
    public void validateProductStock(int productId, int quantity) {
        try {
            Boolean hasStock = productClient.checkProductStock(productId, quantity);
            if (!hasStock) {
                log.error("Producto con ID: {} no tiene stock suficiente para cantidad: {}", productId, quantity);
                throw new ExecptionValidation("Producto con ID " + productId + " no tiene stock suficiente");
            }
        } catch (Exception e) {
            log.error("Error al validar stock del producto con ID: {}", productId, e);
            throw new ExecptionValidation("Error al validar stock del producto: " + e.getMessage());
        }
    }

    /**
     * Actualiza el stock de los productos después de la venta
     */
    public void updateProductStock(List<SaleDetail> saleDetails) {
        for (SaleDetail detail : saleDetails) {
            try {
                Boolean updated = productClient.updateProductStock(detail.getProduct_id(), detail.getQuantity());
                if (!updated) {
                    log.error("No se pudo actualizar el stock del producto con ID: {}", detail.getProduct_id());
                    throw new ExecptionValidation("Error al actualizar stock del producto ID: " + detail.getProduct_id());
                }
            } catch (Exception e) {
                log.error("Error al actualizar stock del producto con ID: {}", detail.getProduct_id(), e);
                throw new ExecptionValidation("Error al actualizar stock: " + e.getMessage());
            }
        }
    }
    /**
     * Actualiza una venta existente con datos del DTO
     */
    public void updateSaleFromDTO(Sale existingSale, SaleRequestDTO dto) {
        existingSale.setClient_id(dto.getClient_id());

        // Recalcular totales si hay nuevos detalles
        if (dto.getSale_details() != null && !dto.getSale_details().isEmpty()) {
            validateAndProcessSaleDetails(dto.getSale_details());

            double subtotal = dto.getSale_details().stream()
                    .mapToDouble(SaleDetail::getSubtotal)
                    .sum();
            double total = dto.getSale_details().stream()
                    .mapToDouble(SaleDetail::getTotal)
                    .sum();

            existingSale.setSubtotal(subtotal);
            existingSale.setTotal(total);

            // Actualizar detalles
            dto.getSale_details().forEach(detail -> detail.setSale(existingSale));
            existingSale.setSale_detail(dto.getSale_details());
        }
    }
}
