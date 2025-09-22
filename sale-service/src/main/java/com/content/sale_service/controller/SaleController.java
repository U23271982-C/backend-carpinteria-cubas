package com.content.sale_service.controller;

import com.content.sale_service.dto.Request.SaleRequestDTO;
import com.content.sale_service.dto.Response.SaleResponseDTO;
import com.content.sale_service.service.SaleServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestión de ventas
 * Implementa operaciones CRUD siguiendo principios RESTful
 */
@RestController
@RequestMapping("/api/v1/sales")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class SaleController {
    
    private final SaleServiceImpl saleService;

    /**
     * Crear una nueva venta
     * @param saleRequestDTO Datos de la venta a crear
     * @return Venta creada con código 201
     */
    @PostMapping
    public ResponseEntity<SaleResponseDTO> createSale(@Valid @RequestBody SaleRequestDTO saleRequestDTO) {
        log.info("Recibida solicitud para crear venta para cliente ID: {}", saleRequestDTO.getClient_id());
        
        SaleResponseDTO createdSale = saleService.create(saleRequestDTO);
        
        log.info("Venta creada exitosamente con número: {}", createdSale.getNumber_sale());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSale);
    }

    /**
     * Obtener todas las ventas
     * @return Lista de todas las ventas
     */
    @GetMapping
    public ResponseEntity<List<SaleResponseDTO>> getAllSales() {
        log.info("Recibida solicitud para obtener todas las ventas");
        
        List<SaleResponseDTO> sales = saleService.allList();
        
        log.info("Se encontraron {} ventas", sales.size());
        return ResponseEntity.ok(sales);
    }

    /**
     * Obtener una venta por su ID
     * @param id ID de la venta
     * @return Venta encontrada
     */
    @GetMapping("/{id}")
    public ResponseEntity<SaleResponseDTO> getSaleById(@PathVariable Long id) {
        log.info("Recibida solicitud para obtener venta con ID: {}", id);
        
        SaleResponseDTO sale = saleService.readById(id);
        
        log.info("Venta encontrada con número: {}", sale.getNumber_sale());
        return ResponseEntity.ok(sale);
    }

    /**
     * Actualizar una venta existente
     * @param id ID de la venta a actualizar
     * @param saleRequestDTO Nuevos datos de la venta
     * @return Venta actualizada
     */
    @PutMapping("/{id}")
    public ResponseEntity<SaleResponseDTO> updateSale(
            @PathVariable Integer id,
            @Valid @RequestBody SaleRequestDTO saleRequestDTO) {
        
        log.info("Recibida solicitud para actualizar venta con ID: {}", id);
        
        SaleResponseDTO updatedSale = saleService.update(id, saleRequestDTO);
        
        log.info("Venta actualizada exitosamente con número: {}", updatedSale.getNumber_sale());
        return ResponseEntity.ok(updatedSale);
    }

    /**
     * Eliminar una venta
     * @param id ID de la venta a eliminar
     * @return Respuesta sin contenido (204)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Integer id) {
        log.info("Recibida solicitud para eliminar venta con ID: {}", id);
        
        saleService.remove(id);
        
        log.info("Venta eliminada exitosamente con ID: {}", id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint de salud para verificar que el servicio está funcionando
     * @return Mensaje de estado
     */
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Sale Service está funcionando correctamente");
    }

    /**
     * Obtener ventas por cliente
     * @param clientId ID del cliente
     * @return Lista de ventas del cliente
     */
    @GetMapping("/customer/{clientId}")
    public ResponseEntity<List<SaleResponseDTO>> getSalesByCustomer(@PathVariable Integer clientId) {
        log.info("Recibida solicitud para obtener ventas del cliente ID: {}", clientId);
        
        // Por ahora retornamos todas las ventas, pero se puede implementar filtrado
        List<SaleResponseDTO> sales = saleService.allList();
        List<SaleResponseDTO> customerSales = sales.stream()
                .filter(sale -> sale.getClient_id() == clientId)
                .toList();
        
        log.info("Se encontraron {} ventas para el cliente ID: {}", customerSales.size(), clientId);
        return ResponseEntity.ok(customerSales);
    }

    /**
     * Obtener estadísticas básicas de ventas
     * @return Información estadística
     */
    @GetMapping("/stats")
    public ResponseEntity<SaleStatsDTO> getSalesStats() {
        log.info("Recibida solicitud para obtener estadísticas de ventas");
        
        List<SaleResponseDTO> sales = saleService.allList();
        
        SaleStatsDTO stats = SaleStatsDTO.builder()
                .totalSales(sales.size())
                .totalAmount(sales.stream().mapToDouble(SaleResponseDTO::getTotal).sum())
                .averageAmount(sales.isEmpty() ? 0.0 : 
                    sales.stream().mapToDouble(SaleResponseDTO::getTotal).average().orElse(0.0))
                .build();
        
        log.info("Estadísticas calculadas: {} ventas, total: {}", stats.getTotalSales(), stats.getTotalAmount());
        return ResponseEntity.ok(stats);
    }

    /**
     * DTO para estadísticas de ventas
     */
    public static class SaleStatsDTO {
        private int totalSales;
        private double totalAmount;
        private double averageAmount;

        public static SaleStatsDTOBuilder builder() {
            return new SaleStatsDTOBuilder();
        }

        public static class SaleStatsDTOBuilder {
            private int totalSales;
            private double totalAmount;
            private double averageAmount;

            public SaleStatsDTOBuilder totalSales(int totalSales) {
                this.totalSales = totalSales;
                return this;
            }

            public SaleStatsDTOBuilder totalAmount(double totalAmount) {
                this.totalAmount = totalAmount;
                return this;
            }

            public SaleStatsDTOBuilder averageAmount(double averageAmount) {
                this.averageAmount = averageAmount;
                return this;
            }

            public SaleStatsDTO build() {
                SaleStatsDTO dto = new SaleStatsDTO();
                dto.totalSales = this.totalSales;
                dto.totalAmount = this.totalAmount;
                dto.averageAmount = this.averageAmount;
                return dto;
            }
        }

        // Getters
        public int getTotalSales() { return totalSales; }
        public double getTotalAmount() { return totalAmount; }
        public double getAverageAmount() { return averageAmount; }
    }
}
