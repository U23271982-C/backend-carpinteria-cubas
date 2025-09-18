package com.content.inventario_stock_servicio.service;
import com.content.inventario_stock_servicio.dto.Request.MatterRequestDTO;
import com.content.inventario_stock_servicio.dto.Response.MatterResponseDTO;
import com.content.inventario_stock_servicio.mapper.MatterMapper;
import com.content.inventario_stock_servicio.repository.MatterRepository;
import com.content.inventario_stock_servicio.service.abstractService.ServiceAbs;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatterServiceImpl implements ServiceAbs<MatterRequestDTO, MatterResponseDTO> {
    private final MatterRepository matter_repository;
    private final MatterMapper matter_mapper;

    @Transactional
    @Override
    public MatterResponseDTO create(MatterRequestDTO dto) {
        return null;
    }

    @Transactional
    @Override
    public MatterResponseDTO readById(Long id) {
        return null;
    }

    @Transactional
    @Override
    public void remove(int id) {

    }

    @Transactional
    @Override
    public MatterResponseDTO update(int id,MatterRequestDTO dto) {
        return null;
    }

    @Transactional
    @Override
    public List<MatterResponseDTO> list(Long id) {
        return List.of();
    }
}
