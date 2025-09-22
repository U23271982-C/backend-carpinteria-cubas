package com.content.sale_service.controller;

import com.content.sale_service.dto.Request.SaleRequestDTO;
import com.content.sale_service.dto.Response.SaleResponseDTO;
import com.content.sale_service.entity.SaleDetail;
import com.content.sale_service.service.abstractService.ServiceAbs;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SaleController.class)
class SaleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceAbs<SaleRequestDTO, SaleResponseDTO> saleService;

    @Autowired
    private ObjectMapper objectMapper;

    private SaleRequestDTO saleRequestDTO;
    private SaleResponseDTO saleResponseDTO;

    @BeforeEach
    void setUp() {
        // Setup SaleRequestDTO
        saleRequestDTO = SaleRequestDTO.builder()
                .subtotal(BigDecimal.valueOf(100.00))
                .total(BigDecimal.valueOf(116.00))
                .client_id(1L)
                .state_sale_current_id(1L)
                .state_entity_current_id(1L)
                .saleDetails(Arrays.asList(
                        SaleDetail.builder()
                                .product_id(1L)
                                .quantity(2)
                                .unit_price(BigDecimal.valueOf(50.00))
                                .subtotal(BigDecimal.valueOf(100.00))
                                .build()
                ))
                .build();

        // Setup SaleResponseDTO
        saleResponseDTO = SaleResponseDTO.builder()
                .number_sale("SALE-001")
                .date_sale(LocalDate.now())
                .hour_sale(LocalTime.now())
                .subtotal(BigDecimal.valueOf(100.00))
                .total(BigDecimal.valueOf(116.00))
                .client_id(1L)
                .id_state_sale_current(1L)
                .build();
    }

    @Test
    void createSale_ShouldReturnCreatedSale_WhenValidRequest() throws Exception {
        // Arrange
        when(saleService.create(any(SaleRequestDTO.class)))
                .thenReturn(saleResponseDTO);

        // Act & Assert
        mockMvc.perform(post("/api/v1/sales")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(saleRequestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Venta creada exitosamente"))
                .andExpect(jsonPath("$.data.number_sale").value("SALE-001"))
                .andExpect(jsonPath("$.data.subtotal").value(100.00))
                .andExpect(jsonPath("$.data.total").value(116.00))
                .andExpect(jsonPath("$.data.client_id").value(1));

        verify(saleService).create(any(SaleRequestDTO.class));
    }

    @Test
    void createSale_ShouldReturnBadRequest_WhenInvalidRequest() throws Exception {
        // Arrange
        SaleRequestDTO invalidRequest = SaleRequestDTO.builder()
                .subtotal(null) // Invalid: null subtotal
                .total(BigDecimal.valueOf(116.00))
                .client_id(null) // Invalid: null client_id
                .build();

        // Act & Assert
        mockMvc.perform(post("/api/v1/sales")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest());

        verify(saleService, never()).create(any(SaleRequestDTO.class));
    }

    @Test
    void getAllSales_ShouldReturnSalesList() throws Exception {
        // Arrange
        List<SaleResponseDTO> salesList = Arrays.asList(saleResponseDTO);
        when(saleService.allList()).thenReturn(salesList);

        // Act & Assert
        mockMvc.perform(get("/api/v1/sales")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Ventas obtenidas exitosamente"))
                .andExpected(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[0].number_sale").value("SALE-001"));

        verify(saleService).allList();
    }

    @Test
    void getSaleById_ShouldReturnSale_WhenSaleExists() throws Exception {
        // Arrange
        when(saleService.readById(1L)).thenReturn(saleResponseDTO);

        // Act & Assert
        mockMvc.perform(get("/api/v1/sales/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Venta obtenida exitosamente"))
                .andExpect(jsonPath("$.data.number_sale").value("SALE-001"))
                .andExpect(jsonPath("$.data.client_id").value(1));

        verify(saleService).readById(1L);
    }

    @Test
    void getSaleById_ShouldReturnNotFound_WhenSaleNotExists() throws Exception {
        // Arrange
        when(saleService.readById(999L))
                .thenThrow(new RuntimeException("Venta no encontrada con ID: 999"));

        // Act & Assert
        mockMvc.perform(get("/api/v1/sales/999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("Venta no encontrada"));

        verify(saleService).readById(999L);
    }

    @Test
    void updateSale_ShouldReturnUpdatedSale_WhenValidRequest() throws Exception {
        // Arrange
        when(saleService.update(eq(1L), any(SaleRequestDTO.class)))
                .thenReturn(saleResponseDTO);

        // Act & Assert
        mockMvc.perform(put("/api/v1/sales/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(saleRequestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Venta actualizada exitosamente"))
                .andExpect(jsonPath("$.data.number_sale").value("SALE-001"));

        verify(saleService).update(eq(1L), any(SaleRequestDTO.class));
    }

    @Test
    void deleteSale_ShouldReturnSuccess_WhenSaleExists() throws Exception {
        // Arrange
        when(saleService.remove(1L)).thenReturn(true);

        // Act & Assert
        mockMvc.perform(delete("/api/v1/sales/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Venta eliminada exitosamente"));

        verify(saleService).remove(1L);
    }

    @Test
    void deleteSale_ShouldReturnNotFound_WhenSaleNotExists() throws Exception {
        // Arrange
        when(saleService.remove(999L)).thenReturn(false);

        // Act & Assert
        mockMvc.perform(delete("/api/v1/sales/999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("Venta no encontrada para eliminar"));

        verify(saleService).remove(999L);
    }

    @Test
    void healthCheck_ShouldReturnHealthStatus() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/api/v1/sales/health")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Sale Service está funcionando correctamente"))
                .andExpect(jsonPath("$.data.status").value("UP"))
                .andExpect(jsonPath("$.data.service").value("sale-service"))
                .andExpect(jsonPath("$.data.version").value("1.0.0"));
    }

    @Test
    void getSalesByCustomer_ShouldReturnCustomerSales() throws Exception {
        // Arrange
        List<SaleResponseDTO> customerSales = Arrays.asList(saleResponseDTO);
        when(saleService.allList()).thenReturn(customerSales);

        // Act & Assert
        mockMvc.perform(get("/api/v1/sales/customer/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Ventas del cliente obtenidas exitosamente"))
                .andExpect(jsonPath("$.data").isArray());
    }

    @Test
    void getSalesStats_ShouldReturnStatistics() throws Exception {
        // Arrange
        List<SaleResponseDTO> allSales = Arrays.asList(saleResponseDTO);
        when(saleService.allList()).thenReturn(allSales);

        // Act & Assert
        mockMvc.perform(get("/api/v1/sales/stats")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Estadísticas de ventas obtenidas exitosamente"))
                .andExpect(jsonPath("$.data.totalSales").value(1))
                .andExpect(jsonPath("$.data.totalRevenue").value(116.00));
    }
}