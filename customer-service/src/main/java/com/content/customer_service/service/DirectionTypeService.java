package com.content.customer_service.service;

import com.content.customer_service.dto.request.DirectionTypeRequestDTO;
import com.content.customer_service.dto.response.DirectionTypeResponseDTO;
import com.content.customer_service.exception.EServiceLayer;
import com.content.customer_service.mapper.mapperImpl.DirectionTypeMapper;
import com.content.customer_service.model.DirectionType;
import com.content.customer_service.model.StateEntity;
import com.content.customer_service.repository.DirectionTypeRepository;
import com.content.customer_service.service.abstractService.ServiceAbs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class DirectionTypeService implements ServiceAbs<DirectionTypeRequestDTO, DirectionTypeResponseDTO> {

    private final DirectionTypeMapper directionTypeMapper;
    private final DirectionTypeRepository directionTypeRepository;

    @Override
    public DirectionTypeResponseDTO create(DirectionTypeRequestDTO dto) {
        log.info("DirectionTypeService.create()");
        DirectionType model = directionTypeMapper.toModel(dto);
        model.setState_entity_id(StateEntity.builder().state_entity_id(1).build());
        model = directionTypeRepository.save(model);
        return directionTypeMapper.toDTO(model);
    }

    @Override
    public List<DirectionTypeResponseDTO> allList() {
        log.info("DirectionTypeService.allList()");
        return directionTypeRepository.findAll().stream()
                .filter(directionType -> directionType.getState_entity_id().getState_entity_id() != 3)
                .map(directionTypeMapper::toDTO)
                .toList();
    }

    @Override
    public DirectionTypeResponseDTO readByUUID(UUID uuid) {
        log.info("DirectionTypeService.readByUUID()");
        DirectionType model = searchEntityByUUID(uuid);
        return directionTypeMapper.toDTO(model);
    }

    @Override
    public void remove(UUID uuid) {
        log.info("DirectionTypeService.remove()");
        DirectionType model_existing = searchEntityByUUID(uuid);
        model_existing.setState_entity_id(StateEntity.builder().state_entity_id(3).build());
        directionTypeRepository.save(model_existing);
    }

    @Override
    public DirectionTypeResponseDTO updateByUUID(UUID uuid, DirectionTypeRequestDTO dto) {
        log.info("DirectionTypeService.updateByUUID()");
        DirectionType model_existing = searchEntityByUUID(uuid);
        directionTypeMapper.updateFromDto(dto, model_existing);
        DirectionType model_updated = directionTypeRepository.save(model_existing);
        return directionTypeMapper.toDTO(model_updated);
    }

    private DirectionType searchEntityByUUID(UUID uuid) {
        log.info("DirectionTypeService.searchEntityByUUID()");
        return directionTypeRepository.findByUuid(uuid)
                .orElseThrow(() -> new EServiceLayer(String.format("Tipo de Dirección no encontrado con UUID: " + uuid)));
    }
}
