package com.content.sale_service.service;

import com.content.sale_service.client.ValidateClient;
import com.content.sale_service.dto.Request.SaleRequestDTO;
import com.content.sale_service.dto.Response.SaleResponseDTO;
import com.content.sale_service.dto.builder.BuildDTO;
import com.content.sale_service.mapper.SaleMapper;
import com.content.sale_service.model.Sale;
import com.content.sale_service.repository.SaleRepository;
import com.content.sale_service.service.abstractService.ServiceAbs;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SaleServiceImpl implements ServiceAbs<SaleRequestDTO, SaleResponseDTO> {
    private final SaleRepository saleRepository;
    private final SaleMapper saleMapper;
    private final ValidateClient validateClient;

    @Transactional
    @Override
    public SaleResponseDTO create(SaleRequestDTO dto) {
        log.info("Iniciando creación de venta para cliente ID: {}", dto.getClient_id());
        
        // Validar que el cliente existe
        validateClient.validateCustomerExists(dto.getClient_id());

        // Validar y procesar detalles de la venta
        validateClient.validateAndProcessSaleDetails(dto.getSale_details());

        Sale sale = BuildDTO.buildSaleFromDTO(dto);
        
        // Actualizar stock de productos
        validateClient.updateProductStock(dto.getSale_details());
        
        Sale savedSale = saleRepository.save(sale);
        log.info("Venta creada exitosamente con ID: {} y número: {}", savedSale.getId(), savedSale.getNumber_sale());
        
        return saleMapper.toDTO(savedSale);
    }

    @Transactional
    @Override
    public List<SaleResponseDTO> allList() {
        log.info("Obteniendo todas las ventas");
        List<Sale> sales = saleRepository.findAll();
        return sales.stream()
                .map(saleMapper::toDTO)
                .toList();
    }

    @Transactional
    @Override
    public SaleResponseDTO readById(Long id) {
        log.info("Buscando venta con ID: {}", id);
        Optional<Sale> saleOpt = saleRepository.findById(id.intValue());
        
        if (saleOpt.isEmpty()) {
            log.error("Venta no encontrada con ID: {}", id);
            throw new EntityNotFoundException("Venta no encontrada con ID: " + id);
        }
        
        return saleMapper.toDTO(saleOpt.get());
    }

    @Transactional
    @Override
    public void remove(int id) {
        log.info("Eliminando venta con ID: {}", id);
        
        if (!saleRepository.existsById(id)) {
            log.error("Venta no encontrada con ID: {}", id);
            throw new EntityNotFoundException("Venta no encontrada con ID: " + id);
        }
        
        saleRepository.deleteById(id);
        log.info("Venta eliminada exitosamente con ID: {}", id);
    }

    @Transactional
    @Override
    public SaleResponseDTO update(int id, SaleRequestDTO dto) {
        log.info("Actualizando venta con ID: {}", id);
        
        Optional<Sale> existingSaleOpt = saleRepository.findById(id);
        if (existingSaleOpt.isEmpty()) {
            log.error("Venta no encontrada con ID: {}", id);
            throw new EntityNotFoundException("Venta no encontrada con ID: " + id);
        }
        
        // Validar cliente
        validateClient.validateCustomerExists(dto.getClient_id());
        //validateCustomerExists(dto.getClient_id());
        
        Sale existingSale = existingSaleOpt.get();
        validateClient.updateSaleFromDTO(existingSale, dto);
        
        Sale updatedSale = saleRepository.save(existingSale);
        log.info("Venta actualizada exitosamente con ID: {}", updatedSale.getId());
        
        return saleMapper.toDTO(updatedSale);
    }

}
