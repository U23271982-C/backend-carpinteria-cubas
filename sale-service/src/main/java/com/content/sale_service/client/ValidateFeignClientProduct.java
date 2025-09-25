package com.content.sale_service.client;

import com.content.sale_service.dto.Request.SaleDetailRequestDTO;
import com.content.sale_service.dto.Request.SaleRequestDTO;
import com.content.sale_service.dto.external.ProductDTO;
import com.content.sale_service.execption.EFeignClientCustomerNotFound;
import com.content.sale_service.execption.EFeignClientProductNotFound;
import com.content.sale_service.model.Sale;
import com.content.sale_service.model.SaleDetail;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@AllArgsConstructor
public class ValidateFeignClientProduct {
    private final ProductClient productClient;

    /**
     * Valida y procesa los detalles de la venta
     */
    public List<SaleDetail> processSaleDetails(List<SaleDetailRequestDTO> saleDetailsRequestsDTOs) {
        /**
         * Mapeo de los detalles de la venta a un modelo de la venta <br>
         * 'x' representa cada uno de los detalles de la venta (individualmente)
         */
        return saleDetailsRequestsDTOs.stream()
                .map(
                        x -> {
                            // Calcular subtotal y total del detalle
                            ProductDTO product = productClient.getProductById(x.getId_product()); // Extramos los datos del producto
                            SaleDetail sale_detail_model = new SaleDetail();

                            // Rellenamos el modelo con los datos del DTO
                            int id_product = product.getId();
                            double unit_price = product.getPrice().doubleValue();
                            int quantity = x.getQuantity();
                            double total = unit_price * quantity;

                            sale_detail_model.setProduct_id(id_product);
                            sale_detail_model.setPrice_unit(unit_price);
                            sale_detail_model.setQuantity(quantity);
                            sale_detail_model.setTotal(total);
                            //sale_detail_model.setSubtotal(subtotal);

                            return sale_detail_model;
                        }
                ).toList();
    }

    /**
     * Valida que el producto existe y tiene stock suficiente
     */
    public void validateProductStock(int productId, int quantity) {
        try {
            Boolean hasStock = productClient.checkProductStock(productId, quantity);
            if (!hasStock) {
                log.error("Producto con ID: {} no tiene stock suficiente para cantidad: {}", productId, quantity);
                throw new EFeignClientProductNotFound("Producto con ID " + productId + " no tiene stock suficiente");
            }
        } catch (Exception e) {
            log.error("Error al validar stock del producto con ID: {}", productId, e);
            throw new EFeignClientProductNotFound("Error al validar stock del producto: " + e.getMessage());
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
                    throw new EFeignClientProductNotFound("Error al actualizar stock del producto ID: " + detail.getProduct_id());
                }
            } catch (Exception e) {
                log.error("Error al actualizar stock del producto con ID: {}", detail.getProduct_id(), e);
                throw new EFeignClientProductNotFound("Error al actualizar stock: " + e.getMessage());
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
            processSaleDetails(dto.getSale_details());

            double subtotal = dto.getSale_details().stream()
                    .mapToDouble(SaleDetailRequestDTO::getSubtotal)
                    .sum();
            double total = dto.getSale_details().stream()
                    .mapToDouble(SaleDetailRequestDTO::getTotal)
                    .sum();

            existingSale.setSubtotal(subtotal);
            existingSale.setTotal(total);

            // Actualizar detalles
            dto.getSale_details().forEach(detail -> detail.setSale(existingSale));
            existingSale.setSale_detail(dto.getSale_details());
        }
    }
}
