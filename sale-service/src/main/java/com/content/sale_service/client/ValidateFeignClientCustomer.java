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
import org.springframework.stereotype.Component;

import java.util.List;
@Slf4j
@AllArgsConstructor
@Component
public class ValidateFeignClientCustomer {
    private final CustomerClient customerClient;
    //private final ProductClient productClient;


    /**
     * Valida que el cliente existe usando el Feign client, consultando si exite el id
     */
    public void validateCustomerExists(int customerId) {
        try {
            Boolean customerExists = customerClient.customerExists(customerId);
            if (!customerExists) {
                log.error("Cliente no encontrado con ID: {}", customerId);

                 throw new EFeignClientCustomerNotFound("Cliente no encontrado: " + customerId);
            }
        } catch (Exception e) {
            log.error("Error al validar cliente con ID: {}", customerId, e);

            throw new EFeignClientCustomerNotFound("Error al validar cliente: " + e.getMessage());
        }
    }
}
