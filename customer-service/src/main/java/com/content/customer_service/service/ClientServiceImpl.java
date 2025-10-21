package com.content.customer_service.service;

import com.content.customer_service.dto.request.ClientRequestDTO;
import com.content.customer_service.dto.response.ClientResponseDTO;
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
import java.util.stream.Collectors;

/**
 * Servicio de implementación para gestión de Clientes - USA SOLO UUIDs
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ClientServiceImpl implements ServiceAbs<ClientRequestDTO, ClientResponseDTO> {

    private final ClientRepository clientRepository;
    private final ClientTypeRepository clientTypeRepository;
    private final IdentificationRepository identificationRepository;
    private final StateEntityRepository stateEntityRepository;
    private final ClientMapper clientMapper;
    private final UtilityValidator utilityValidator;

    @Transactional
    @Override
    public ClientResponseDTO create(ClientRequestDTO dto) {
        log.info("Iniciando creación de cliente: {} {}", dto.getClientName(), dto.getClientLastName());

        // Validar datos del cliente
        utilityValidator.validate(dto);

        // Verificar que el tipo de cliente existe usando UUID
        ClientType clientType = clientTypeRepository.findByUuid(dto.getClientTypeUuid())
                .orElseThrow(() -> new EntityNotFoundException("Tipo de cliente no encontrado con UUID: " + dto.getClientTypeUuid()));

        // Verificar que la identificación existe usando UUID
        Identification identification = identificationRepository.findByUuid(dto.getIdentificationUuid())
                .orElseThrow(() -> new EntityNotFoundException("Identificación no encontrada con UUID: " + dto.getIdentificationUuid()));

        // Verificar que el estado existe usando UUID
        StateEntity stateEntity = stateEntityRepository.findByUuid(dto.getStateEntityUuid())
                .orElseThrow(() -> new EntityNotFoundException("Estado no encontrado con UUID: " + dto.getStateEntityUuid()));

        // Crear el cliente
        Client client = Client.builder()
                .client_name(dto.getClientName())
                .client_last_name(dto.getClientLastName())
                .registration_date(LocalDateTime.now())
                .client_type_id(clientType)
                .identification_id(identification)
                .state_entity_id(stateEntity)
                .build();

        Client savedClient = clientRepository.save(client);

        log.info("Cliente creado exitosamente con UUID: {}", savedClient.getUuid());
        return convertToResponseDTO(savedClient);
    }

    @Override
    public ClientResponseDTO getByUuid(String uuid) {
        log.info("Buscando cliente por UUID: {}", uuid);

        Client client = clientRepository.findActiveByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con UUID: " + uuid));

        return convertToResponseDTO(client);
    }

    @Override
    public List<ClientResponseDTO> getAll() {
        log.info("Obteniendo todos los clientes activos");

        List<Client> clients = clientRepository.findAllActive();
        return clients.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public ClientResponseDTO update(String uuid, ClientRequestDTO dto) {
        log.info("Actualizando cliente con UUID: {}", uuid);

        // Buscar cliente por UUID
        Client client = clientRepository.findActiveByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con UUID: " + uuid));

        // Validar datos
        utilityValidator.validate(dto);

        // Buscar entidades relacionadas por UUID
        ClientType clientType = clientTypeRepository.findByUuid(dto.getClientTypeUuid())
                .orElseThrow(() -> new EntityNotFoundException("Tipo de cliente no encontrado"));

        Identification identification = identificationRepository.findByUuid(dto.getIdentificationUuid())
                .orElseThrow(() -> new EntityNotFoundException("Identificación no encontrada"));

        StateEntity stateEntity = stateEntityRepository.findByUuid(dto.getStateEntityUuid())
                .orElseThrow(() -> new EntityNotFoundException("Estado no encontrado"));

        // Actualizar datos
        client.setClient_name(dto.getClientName());
        client.setClient_last_name(dto.getClientLastName());
        client.setClient_type_id(clientType);
        client.setIdentification_id(identification);
        client.setState_entity_id(stateEntity);

        Client updatedClient = clientRepository.save(client);

        log.info("Cliente actualizado exitosamente con UUID: {}", uuid);
        return convertToResponseDTO(updatedClient);
    }

    @Transactional
    @Override
    public void delete(String uuid) {
        log.info("Eliminando cliente con UUID: {}", uuid);

        Client client = clientRepository.findActiveByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con UUID: " + uuid));

        // Soft delete: cambiar a estado inactivo
        StateEntity inactiveState = stateEntityRepository.findByStateName("INACTIVO")
                .orElseThrow(() -> new EntityNotFoundException("Estado INACTIVO no encontrado"));

        client.setState_entity_id(inactiveState);
        clientRepository.save(client);

        log.info("Cliente eliminado (soft delete) exitosamente con UUID: {}", uuid);
    }

    // Método auxiliar para convertir entidad a DTO de respuesta
    private ClientResponseDTO convertToResponseDTO(Client client) {
        return ClientResponseDTO.builder()
                .uuid(client.getUuid()) // Solo exponemos UUID, nunca el ID interno
                .clientName(client.getClient_name())
                .clientLastName(client.getClient_last_name())
                .registrationDate(client.getRegistration_date())
                .clientTypeUuid(client.getClient_type_id().getUuid())
                .clientTypeName(client.getClient_type_id().getType_name())
                .identificationUuid(client.getIdentification_id().getUuid())
                .identificationNumber(client.getIdentification_id().getIdentification_number())
                .identificationTypeName(client.getIdentification_id().getIdentification_type_id().getType_name())
                .stateEntityUuid(client.getState_entity_id().getUuid())
                .stateName(client.getState_entity_id().getState_name())
                .build();
    }

    // Método para buscar por número de identificación
    public ClientResponseDTO getByIdentificationNumber(String identificationNumber) {
        log.info("Buscando cliente por número de identificación: {}", identificationNumber);

        Client client = clientRepository.findByIdentificationNumber(identificationNumber)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con número de identificación: " + identificationNumber));

        return convertToResponseDTO(client);
    }

    // Método para buscar por nombre
    public List<ClientResponseDTO> searchByName(String searchTerm) {
        log.info("Buscando clientes por nombre: {}", searchTerm);

        List<Client> clients = clientRepository.findByNameContainingIgnoreCase(searchTerm);
        return clients.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }
}
