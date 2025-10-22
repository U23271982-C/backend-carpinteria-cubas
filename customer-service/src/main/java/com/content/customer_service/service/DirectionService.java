package com.content.customer_service.service;

import com.content.customer_service.dto.request.DirectionRequestDTO;
import com.content.customer_service.dto.response.DirectionResponseDTO;
import com.content.customer_service.exception.EServiceLayer;
import com.content.customer_service.mapper.mapperImpl.DirectionMapper;
import com.content.customer_service.model.*;
import com.content.customer_service.repository.*;
import com.content.customer_service.service.abstractService.ServiceAbs;
import com.content.customer_service.util.UtilityValidator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Servicio de implementación para gestión de Direcciones
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DirectionService implements ServiceAbs<DirectionRequestDTO, DirectionResponseDTO> {

    private final DirectionRepository directionRepository;
    private final DirectionMapper directionMapper;
    private final ClientRepository clientRepository;
    private final DirectionTypeRepository directionTypeRepository;
    private final DistrictRepository districtRepository;

    @Transactional
    @Override
    public DirectionResponseDTO create(DirectionRequestDTO dto) {
        log.info("DirectionService.create()");
        Client clientReading =
                clientRepository.findByUuid(dto.getClient_uuid())
                        .orElseThrow(() -> new EServiceLayer("El cliente no existe"));
        DirectionType directionTypeReading =
                directionTypeRepository.findByUuid(dto.getDirection_type_uuid())
                        .orElseThrow(() -> new EServiceLayer("El tipo de dirección no existe"));
        District districtReading =
                districtRepository.findByUuid(dto.getDistrict_uuid())
                        .orElseThrow(() -> new EServiceLayer("El distrito no existe"));
        // Convertimos de DTO a modelo
        Direction model = directionMapper.toModel(dto);
        model.setClient_id(clientReading);
        model.setDirection_type_id(directionTypeReading);
        model.setDistrict_id(districtReading);
        model.setState_entity_id(StateEntity.builder().state_entity_id(1).build()); // ACTIVO
        model = directionRepository.save(model);
        return directionMapper.toDTO(model);
    }

    @Transactional
    @Override
    public List<DirectionResponseDTO> allList() {
        log.info("DirectionService.allList()");

        return directionRepository.findAll().stream()
                .filter(direction -> direction.getState_entity_id().getState_entity_id() != 3) // Excluir eliminados;
                .map(directionMapper::toDTO)
                .toList();
    }

    @Transactional
    @Override
    public DirectionResponseDTO readByUUID(UUID uuid) {
        log.info("DirectionService.readByUUID()");
        Direction model = searchEntityByUUID(uuid);
        return directionMapper.toDTO(model);
    }

    @Transactional
    @Override
    public void remove(UUID uuid) {
        log.info("DirectionService.remove()");
        Direction model_exiting = searchEntityByUUID(uuid);
        model_exiting.setState_entity_id(StateEntity.builder().state_entity_id(3).build()); // INACTIVO
        directionRepository.save(model_exiting);
    }

    @Transactional
    @Override
    public DirectionResponseDTO updateByUUID(UUID uuid, DirectionRequestDTO dto) {
        log.info("DirectionService.updateByUUID()");
        Direction model = searchEntityByUUID(uuid);
        if (dto.getClient_uuid() != null) {
            Client clientReading =
                    clientRepository.findByUuid(dto.getClient_uuid())
                            .orElseThrow(() -> new EServiceLayer("El cliente no existe"));
            model.setClient_id(clientReading);
        }if (dto.getDirection_type_uuid() != null) {
            DirectionType directionTypeReading =
                    directionTypeRepository.findByUuid(dto.getDirection_type_uuid())
                            .orElseThrow(() -> new EServiceLayer("El tipo de dirección no existe"));
            model.setDirection_type_id(directionTypeReading);
        }if (dto.getDistrict_uuid() != null) {
            District districtReading =
                    districtRepository.findByUuid(dto.getDistrict_uuid())
                            .orElseThrow(() -> new EServiceLayer("El distrito no existe"));
            model.setDistrict_id(districtReading);
        }
        directionMapper.updateFromDto(dto, model);
        model = directionRepository.save(model);
        return directionMapper.toDTO(model);
    }

    private Direction searchEntityByUUID(UUID uuid) {
        log.info("DirectionService.searchEntityByUUID()");
        return directionRepository.findByUuid(uuid)
                .orElseThrow(() -> new EServiceLayer("La dirección con UUID " + uuid + " no existe"));
    }
}
