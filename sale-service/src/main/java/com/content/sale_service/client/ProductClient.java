package com.content.sale_service.client;

import com.content.sale_service.dto.external.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Feign Client para comunicación con product-service
 */
@FeignClient(name = "product-service", path = "/product")
public interface ProductClient {
    
    /**
     * Obtiene información de un producto por su ID
     * @param productId ID del producto
     * @return Información del producto
     */
    @GetMapping("/{productId}")
    ProductDTO getProductById(@PathVariable("productId") Integer productId);
    
    /**
     * Verifica si un producto existe y tiene stock disponible
     * @param productId ID del producto
     * @param quantity Cantidad requerida
     * @return true si el producto existe y tiene stock suficiente
     */
    @GetMapping("/check-stock/{productId}")
    Boolean checkProductStock(@PathVariable("productId") Integer productId, 
                             @RequestParam("quantity") Integer quantity);
    
    /**
     * Actualiza el stock de un producto después de una venta
     * @param productId ID del producto
     * @param quantity Cantidad a descontar del stock
     * @return true si la actualización fue exitosa
     */
    @PutMapping("/update-stock/{productId}")
    Boolean updateProductStock(@PathVariable("productId") Integer productId, 
                              @RequestParam("quantity") Integer quantity);
}