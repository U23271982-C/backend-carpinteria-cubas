package com.content.sale_service.service;

import com.content.sale_service.dto.Request.SaleRequestDTO;
import com.content.sale_service.dto.Response.SaleResponseDTO;
import com.content.sale_service.mapper.SaleMapper;
import com.content.sale_service.repository.SaleRepository;
import com.content.sale_service.service.abstractService.ServiceAbs;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements ServiceAbs<SaleRequestDTO,SaleResponseDTO> {
    private final SaleRepository sale_repository;
    private final SaleMapper sale_mapper;

    @Transactional
    @Override
    public SaleResponseDTO create(SaleRequestDTO dto) {
        return null;
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
