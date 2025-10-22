package com.content.customer_service.service;

import com.content.customer_service.dto.request.ClientRequestDTO;
import com.content.customer_service.dto.response.ClientResponseDTO;
import com.content.customer_service.exception.EServiceLayer;
import com.content.customer_service.mapper.mapperImpl.ClientMapper;
import com.content.customer_service.model.Client;
import com.content.customer_service.model.ClientType;
import com.content.customer_service.model.Identification;
import com.content.customer_service.model.StateEntity;
import com.content.customer_service.repository.ClientRepository;
import com.content.customer_service.repository.ClientTypeRepository;
import com.content.customer_service.repository.IdentificationRepository;
import com.content.customer_service.repository.StateEntityRepository;
import com.content.customer_service.service.abstractService.ServiceAbs;
import com.content.customer_service.util.UtilityValidator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Servicio de implementación para gestión de Clientes - USA SOLO UUIDs
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ClientService implements ServiceAbs<ClientRequestDTO, ClientResponseDTO> {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final ClientTypeRepository clientTypeRepository;
    private final IdentificationRepository identificationRepository;

    @Transactional
    @Override
    public ClientResponseDTO create(ClientRequestDTO dto) {
        log.info("ClientService.create()");
        ClientType clientTypeReading =
                clientTypeRepository.findByUuid(dto.getClient_type_uuid())
                        .orElseThrow(() -> new EServiceLayer("El tipo de cliente no existe"));
        Identification identificationReading =
                identificationRepository.findByUuid(dto.getIdentification_uuid())
                        .orElseThrow(() -> new EServiceLayer("La identificación no existe"));
        // Convertimos de DTO a modelo
        Client model = clientMapper.toModel(dto);
        model.setState_entity_id(StateEntity.builder().state_entity_id(1).build()); // ACTIVO
        model.setRegistration_date(LocalDateTime.now());
        model.setClient_type_id(clientTypeReading);
        model.setIdentification_id(identificationReading);
        model = clientRepository.save(model);
        return clientMapper.toDTO(model);
    }

    @Transactional
    @Override
    public List<ClientResponseDTO> allList() {
        log.info("ClientService.allList()");
        return clientRepository.findAll().stream()
                .filter(client -> client.getState_entity_id().getState_entity_id() !=3) // Solo activos
                .map(clientMapper::toDTO)
                .toList();
    }

    @Transactional
    @Override
    public ClientResponseDTO readByUUID(UUID uuid) {
        log.info("ClientService.readByUUID()");
        Client model = searchEntityByUUID(uuid);
        return clientMapper.toDTO(model);
    }

    @Transactional
    @Override
    public void remove(UUID uuid) {
        log.info("ClientService.remove()");
        Client model_exiting = searchEntityByUUID(uuid);
        model_exiting.setState_entity_id(StateEntity.builder().state_entity_id(3).build()); // INACTIVO
        clientRepository.save(model_exiting);
    }

    @Transactional
    @Override
    public ClientResponseDTO updateByUUID(UUID uuid, ClientRequestDTO dto) {
        log.info("ClientService.updateByUUID()");
        Client model = searchEntityByUUID(uuid);
        if (dto.getClient_type_uuid() != null) {
            ClientType clientTypeReading =
                    clientTypeRepository.findByUuid(dto.getClient_type_uuid())
                            .orElseThrow(() -> new EServiceLayer("El tipo de cliente no existe"));
            model.setClient_type_id(clientTypeReading);
        }if (dto.getIdentification_uuid() != null) {
            Identification identificationReading =
                    identificationRepository.findByUuid(dto.getIdentification_uuid())
                            .orElseThrow(() -> new EServiceLayer("La identificación no existe"));
            model.setIdentification_id(identificationReading);
        }
        clientMapper.updateFromDto(dto, model);
        model = clientRepository.save(model);
        return clientMapper.toDTO(model);
    }

    private Client searchEntityByUUID(UUID uuid) {
        log.info("ClientService.searchEntityByUUID()");
        return clientRepository.findByUuid(uuid)
                .orElseThrow(() -> new EServiceLayer("El cliente con UUID " + uuid + " no existe"));
    }
}
