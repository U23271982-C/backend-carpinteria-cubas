package com.content.customer_service.service;

import com.content.customer_service.dto.request.DirectionRequestDTO;
import com.content.customer_service.dto.response.DirectionResponseDTO;
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

/**
 * Servicio de implementación para gestión de Direcciones
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DirectionService implements ServiceAbs<DirectionRequestDTO, DirectionResponseDTO> {

    private final DirectionRepository directionRepository;
    private final ClientRepository clientRepository;
    private final DirectionTypeRepository directionTypeRepository;
    private final DistrictRepository districtRepository;
    private final StateEntityRepository stateEntityRepository;
    private final DirectionMapper directionMapper;
    private final UtilityValidator utilityValidator;

    @Transactional
    @Override
    public DirectionResponseDTO create(DirectionRequestDTO dto) {
        log.info("Iniciando creación de dirección para cliente UUID: {}", dto.getClient_id());

        // Validar datos de la dirección
        utilityValidator.validate(dto);

        // Verificar que el cliente existe usando UUID
        Client client = clientRepository.findByUuid(dto.getClient_id())
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con UUID: " + dto.getClient_id()));

        // Verificar que el tipo de dirección existe usando UUID
        DirectionType directionType = directionTypeRepository.findByUuid(dto.getDirection_type_id())
                .orElseThrow(() -> new EntityNotFoundException("Tipo de dirección no encontrado con UUID: " + dto.getDirection_type_id()));

        // Obtener estado activo usando UUID
        StateEntity stateActive = stateEntityRepository.findByUuid(dto.getStateEntityUuid())
                .orElseThrow(() -> new EntityNotFoundException("Estado no encontrado con UUID: " + dto.getStateEntityUuid()));

        // Obtener distrito usando UUID (ya no creamos automáticamente)
        District district = districtRepository.findByUuid(dto.getDistrictUuid())
                .orElseThrow(() -> new EntityNotFoundException("Distrito no encontrado con UUID: " + dto.getDistrictUuid()));

        // Crear la dirección
        Direction direction = directionMapper.toModel(dto);
        direction.setClient_id(client);
        direction.setDirection_type_id(directionType);
        direction.setDistrict_id(district);
        direction.setState_entity_id(stateActive);

        Direction savedDirection = directionRepository.save(direction);
        log.info("Dirección creada exitosamente con UUID: {}", savedDirection.getUuid());

        return directionMapper.toDTO(savedDirection);
    }

    @Override
    public DirectionResponseDTO getByUuid(String uuid) {
        log.info("Buscando dirección con UUID: {}", uuid);
        Direction direction = directionRepository.findActiveByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Dirección no encontrada con UUID: " + uuid));
        return directionMapper.toDTO(direction);
    }

    @Override
    public List<DirectionResponseDTO> getAll() {
        log.info("Obteniendo todas las direcciones activas");
        List<Direction> directions = directionRepository.findAllActive();
        return directions.stream()
                .map(directionMapper::toDTO)
                .toList();
    }

    @Transactional
    @Override
    public DirectionResponseDTO update(String uuid, DirectionRequestDTO dto) {
        log.info("Actualizando dirección con UUID: {}", uuid);

        // Validar datos de la dirección
        utilityValidator.validate(dto);

        // Verificar que la dirección existe
        Direction existingDirection = directionRepository.findActiveByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Dirección no encontrada con UUID: " + uuid));

        // Verificar que el cliente existe usando UUID
        Client client = clientRepository.findByUuid(dto.getClient_id())
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con UUID: " + dto.getClient_id()));

        // Verificar que el tipo de dirección existe usando UUID
        DirectionType directionType = directionTypeRepository.findByUuid(dto.getDirection_type_id())
                .orElseThrow(() -> new EntityNotFoundException("Tipo de dirección no encontrado con UUID: " + dto.getDirection_type_id()));

        // Obtener distrito usando UUID
        District district = districtRepository.findByUuid(dto.getDistrictUuid())
                .orElseThrow(() -> new EntityNotFoundException("Distrito no encontrado con UUID: " + dto.getDistrictUuid()));

        // Estado permanece el mismo o se puede actualizar
        StateEntity stateEntity = stateEntityRepository.findByUuid(dto.getStateEntityUuid())
                .orElseThrow(() -> new EntityNotFoundException("Estado no encontrado con UUID: " + dto.getStateEntityUuid()));

        // Actualizar datos de la dirección
        existingDirection.setClient_id(client);
        existingDirection.setDirection_type_id(directionType);
        existingDirection.setDirection_name(dto.getDirection_name());
        existingDirection.setAddress_line_1(dto.getDirection_number());
        existingDirection.setAddress_line_2(dto.getReference());
        existingDirection.setDistrict_id(district);
        existingDirection.setState_entity_id(stateEntity);

        Direction updatedDirection = directionRepository.save(existingDirection);
        log.info("Dirección actualizada exitosamente con UUID: {}", updatedDirection.getUuid());

        return directionMapper.toDTO(updatedDirection);
    }

    @Transactional
    @Override
    public void delete(String uuid) {
        log.info("Eliminando (lógicamente) dirección con UUID: {}", uuid);

        Direction direction = directionRepository.findActiveByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Dirección no encontrada con UUID: " + uuid));

        // Eliminación lógica: cambiar estado a eliminado
        StateEntity stateDeleted = stateEntityRepository.findByStateName("Eliminado")
                .orElseThrow(() -> new EntityNotFoundException("Estado eliminado no encontrado"));

        direction.setState_entity_id(stateDeleted);
        directionRepository.save(direction);
        log.info("Dirección eliminada (lógicamente) exitosamente con UUID: {}", uuid);
    }
}
