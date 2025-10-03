package com.content.inventory_matter_service.service;
import com.content.inventory_matter_service.dto.Request.MatterRequestDTO;
import com.content.inventory_matter_service.dto.Response.MatterResponseDTO;
import com.content.inventory_matter_service.mapper.MatterMapper;
import com.content.inventory_matter_service.service.abstractService.ServiceAbs;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatterServiceImpl implements ServiceAbs<MatterRequestDTO, MatterResponseDTO> {
    private final MatterMapper matterMapper;

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
