package com.content.sale_service.client;

import com.content.sale_service.dto.external.CustomerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign Client para comunicación con customer-service
 */
@FeignClient(name = "customer-service", path = "/customer")
public interface CustomerClient {
    
    /**
     * Obtiene información de un cliente por su ID
     * @param customerId ID del cliente
     * @return Información del cliente
     */
    @GetMapping("/{customerId}")
    CustomerDTO getCustomerById(@PathVariable("customerId") Integer customerId);
    
    /**
     * Verifica si un cliente existe
     * @param customerId ID del cliente
     * @return true si el cliente existe, false en caso contrario
     */
    @GetMapping("/exists/{customerId}")
    Boolean customerExists(@PathVariable("customerId") Integer customerId);
}