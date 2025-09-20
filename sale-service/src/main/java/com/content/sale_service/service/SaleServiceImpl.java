package com.content.sale_service.service;

import com.content.sale_service.dto.Request.SaleRequestDTO;
import com.content.sale_service.dto.Response.SaleResponseDTO;
import com.content.sale_service.mapper.SaleMapper;
import com.content.sale_service.model.Sale;
import com.content.sale_service.model.SaleDetail;
import com.content.sale_service.model.SaleState;
import com.content.sale_service.repository.SaleRepository;
import com.content.sale_service.service.abstractService.ServiceAbs;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements ServiceAbs<SaleRequestDTO,SaleResponseDTO> {
    private final SaleRepository sale_repository;
    private final SaleMapper sale_mapper;

    @Transactional
    @Override
    public SaleResponseDTO create(SaleRequestDTO dto) {

        Sale sale = new Sale();
        // id lo pone automaticamente en la bd
        // Falta el number Sale
        sale.setDate_sale(LocalDate.now());
        sale.setHour_sale(LocalTime.now());

        double subtotalSale = dto.getSale_details()
                .stream().mapToDouble(SaleDetail::getSubtotal).sum();
        sale.setSubtotal(subtotalSale);

        double totalSale = dto.getSale_details()
                .stream().mapToDouble(SaleDetail::getTotal).sum();
        sale.setTotal(totalSale);

        // Por defecto el state_entity es ACTIVO (activo)
        // Por defecto el state_sale es 1 (activo)

        //SaleState saleState = new SaleState().builder().description(1).build();
        /**
         * El repositorio guarda el modelo y devuelve el modelo guardado
         */
        //sale.setSale_state();//.setId(1));
        Sale saleSaved = sale_repository.save(sale); // Guardar BD

        // se mapea el modelo guardado a DTO para devolverlo al cliente
        SaleResponseDTO saleResponseDTO = sale_mapper.toDTO(saleSaved);
        return saleResponseDTO;
    }

    @Transactional
    @Override
    public List<SaleResponseDTO> allList() {
        return List.of();
    }

    @Transactional
    @Override
    public SaleResponseDTO readById(Long id) {
        return null;
    }

    @Transactional
    @Override
    public void remove(int id) {

    }

    @Transactional
    @Override
    public SaleResponseDTO update(int id, SaleRequestDTO dto) {
        return null;
    }
}
