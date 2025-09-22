package com.content.sale_service.service;

import com.content.sale_service.client.CustomerClient;
import com.content.sale_service.client.ProductClient;
import com.content.sale_service.dto.Request.SaleRequestDTO;
import com.content.sale_service.dto.Response.SaleResponseDTO;
import com.content.sale_service.dto.external.CustomerDTO;
import com.content.sale_service.dto.external.ProductDTO;
import com.content.sale_service.entity.Sale;
import com.content.sale_service.entity.SaleDetail;
import com.content.sale_service.repository.SaleRepository;
import com.content.sale_service.service.impl.SaleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SaleServiceImplTest {

    @Mock
    private SaleRepository saleRepository;

    @Mock
    private CustomerClient customerClient;

    @Mock
    private ProductClient productClient;

    @InjectMocks
    private SaleServiceImpl saleService;

    private SaleRequestDTO saleRequestDTO;
    private Sale sale;
    private CustomerDTO customerDTO;
    private ProductDTO productDTO;

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

        // Setup Sale entity
        sale = Sale.builder()
                .id(1L)
                .number_sale("SALE-001")
                .date_sale(LocalDate.now())
                .hour_sale(LocalTime.now())
                .subtotal(BigDecimal.valueOf(100.00))
                .total(BigDecimal.valueOf(116.00))
                .client_id(1L)
                .id_state_sale_current(1L)
                .saleDetails(Arrays.asList(
                        SaleDetail.builder()
                                .id(1L)
                                .product_id(1L)
                                .quantity(2)
                                .unit_price(BigDecimal.valueOf(50.00))
                                .subtotal(BigDecimal.valueOf(100.00))
                                .build()
                ))
                .build();

        // Setup CustomerDTO
        customerDTO = CustomerDTO.builder()
                .id(1L)
                .name("Juan")
                .lastName("Pérez")
                .email("juan.perez@test.com")
                .phone("555-0123")
                .address("Calle Test 123")
                .active(true)
                .build();

        // Setup ProductDTO
        productDTO = ProductDTO.builder()
                .id(1L)
                .name("Producto Test")
                .description("Descripción del producto")
                .price(BigDecimal.valueOf(50.00))
                .stock(10)
                .category("Categoría Test")
                .active(true)
                .build();
    }

    @Test
    void create_ShouldCreateSaleSuccessfully_WhenValidData() {
        // Arrange
        when(customerClient.getCustomerById(1L))
                .thenReturn(ResponseEntity.ok(customerDTO));
        when(productClient.getProductById(1L))
                .thenReturn(ResponseEntity.ok(productDTO));
        when(productClient.checkStock(1L))
                .thenReturn(ResponseEntity.ok(Map.of("stock", 10, "available", true)));
        when(saleRepository.save(any(Sale.class)))
                .thenReturn(sale);
        when(productClient.updateStock(eq(1L), any()))
                .thenReturn(ResponseEntity.ok(Map.of("updated", true)));

        // Act
        SaleResponseDTO result = saleService.create(saleRequestDTO);

        // Assert
        assertNotNull(result);
        assertEquals("SALE-001", result.getNumber_sale());
        assertEquals(BigDecimal.valueOf(100.00), result.getSubtotal());
        assertEquals(BigDecimal.valueOf(116.00), result.getTotal());
        assertEquals(1L, result.getClient_id());
        
        verify(saleRepository).save(any(Sale.class));
        verify(productClient).updateStock(eq(1L), any());
    }

    @Test
    void create_ShouldThrowException_WhenCustomerNotFound() {
        // Arrange
        when(customerClient.getCustomerById(1L))
                .thenReturn(ResponseEntity.notFound().build());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, 
                () -> saleService.create(saleRequestDTO));
        
        assertTrue(exception.getMessage().contains("Cliente no encontrado"));
        verify(saleRepository, never()).save(any(Sale.class));
    }

    @Test
    void create_ShouldThrowException_WhenProductNotFound() {
        // Arrange
        when(customerClient.getCustomerById(1L))
                .thenReturn(ResponseEntity.ok(customerDTO));
        when(productClient.getProductById(1L))
                .thenReturn(ResponseEntity.notFound().build());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, 
                () -> saleService.create(saleRequestDTO));
        
        assertTrue(exception.getMessage().contains("Producto no encontrado"));
        verify(saleRepository, never()).save(any(Sale.class));
    }

    @Test
    void create_ShouldThrowException_WhenInsufficientStock() {
        // Arrange
        when(customerClient.getCustomerById(1L))
                .thenReturn(ResponseEntity.ok(customerDTO));
        when(productClient.getProductById(1L))
                .thenReturn(ResponseEntity.ok(productDTO));
        when(productClient.checkStock(1L))
                .thenReturn(ResponseEntity.ok(Map.of("stock", 1, "available", false)));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, 
                () -> saleService.create(saleRequestDTO));
        
        assertTrue(exception.getMessage().contains("Stock insuficiente"));
        verify(saleRepository, never()).save(any(Sale.class));
    }

    @Test
    void readById_ShouldReturnSale_WhenSaleExists() {
        // Arrange
        when(saleRepository.findById(1L))
                .thenReturn(Optional.of(sale));

        // Act
        SaleResponseDTO result = saleService.readById(1L);

        // Assert
        assertNotNull(result);
        assertEquals("SALE-001", result.getNumber_sale());
        assertEquals(BigDecimal.valueOf(100.00), result.getSubtotal());
        assertEquals(BigDecimal.valueOf(116.00), result.getTotal());
    }

    @Test
    void readById_ShouldThrowException_WhenSaleNotFound() {
        // Arrange
        when(saleRepository.findById(1L))
                .thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, 
                () -> saleService.readById(1L));
        
        assertTrue(exception.getMessage().contains("Venta no encontrada"));
    }

    @Test
    void allList_ShouldReturnAllSales() {
        // Arrange
        List<Sale> sales = Arrays.asList(sale);
        when(saleRepository.findAll())
                .thenReturn(sales);

        // Act
        List<SaleResponseDTO> result = saleService.allList();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("SALE-001", result.get(0).getNumber_sale());
    }

    @Test
    void remove_ShouldDeleteSale_WhenSaleExists() {
        // Arrange
        when(saleRepository.findById(1L))
                .thenReturn(Optional.of(sale));
        doNothing().when(saleRepository).deleteById(1L);

        // Act
        Boolean result = saleService.remove(1L);

        // Assert
        assertTrue(result);
        verify(saleRepository).deleteById(1L);
    }

    @Test
    void remove_ShouldReturnFalse_WhenSaleNotFound() {
        // Arrange
        when(saleRepository.findById(1L))
                .thenReturn(Optional.empty());

        // Act
        Boolean result = saleService.remove(1L);

        // Assert
        assertFalse(result);
        verify(saleRepository, never()).deleteById(any());
    }

    @Test
    void update_ShouldUpdateSale_WhenValidData() {
        // Arrange
        when(saleRepository.findById(1L))
                .thenReturn(Optional.of(sale));
        when(customerClient.getCustomerById(1L))
                .thenReturn(ResponseEntity.ok(customerDTO));
        when(saleRepository.save(any(Sale.class)))
                .thenReturn(sale);

        // Act
        SaleResponseDTO result = saleService.update(1L, saleRequestDTO);

        // Assert
        assertNotNull(result);
        verify(saleRepository).save(any(Sale.class));
    }

    @Test
    void calculateSubtotal_ShouldCalculateCorrectly() {
        // Arrange
        List<SaleDetail> details = Arrays.asList(
                SaleDetail.builder()
                        .quantity(2)
                        .unit_price(BigDecimal.valueOf(50.00))
                        .build(),
                SaleDetail.builder()
                        .quantity(1)
                        .unit_price(BigDecimal.valueOf(30.00))
                        .build()
        );

        // Act
        BigDecimal result = saleService.calculateSubtotal(details);

        // Assert
        assertEquals(BigDecimal.valueOf(130.00), result);
    }

    @Test
    void calculateTotal_ShouldAddTaxCorrectly() {
        // Arrange
        BigDecimal subtotal = BigDecimal.valueOf(100.00);

        // Act
        BigDecimal result = saleService.calculateTotal(subtotal);

        // Assert
        assertEquals(BigDecimal.valueOf(116.00), result);
    }

    @Test
    void generateSaleNumber_ShouldGenerateUniqueNumber() {
        // Act
        String result = saleService.generateSaleNumber();

        // Assert
        assertNotNull(result);
        assertTrue(result.startsWith("SALE-"));
        assertTrue(result.length() > 5);
    }
}