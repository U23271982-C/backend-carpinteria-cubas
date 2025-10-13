package com.content.customer_service.service;

import com.content.customer_service.dto.request.ClientRequestDTO;
import com.content.customer_service.dto.response.ClientResponseDTO;
import com.content.customer_service.mapper.ClientMapper;
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

import java.util.List;
import java.util.Optional;

/**
 * Servicio de implementación para gestión de Clientes
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
        log.info("Iniciando creación de cliente: {} {}", dto.getClient_name(), dto.getClient_last_name());

        // Validar datos del cliente
        utilityValidator.validate(dto);

        // Verificar que el tipo de cliente existe
        ClientType clientType = clientTypeRepository.findById(dto.getClient_type_id())
                .orElseThrow(() -> new EntityNotFoundException("Tipo de cliente no encontrado con ID: " + dto.getClient_type_id()));

        // Verificar que la identificación existe
        Identification identification = identificationRepository.findById(dto.getIdentification_id())
                .orElseThrow(() -> new EntityNotFoundException("Identificación no encontrada con ID: " + dto.getIdentification_id()));

        // Verificar que no exista un cliente con el mismo documento de identificación
        if (clientRepository.existsByIdentificationDoc(identification.getIdentification_doc())) {
            throw new IllegalArgumentException("Ya existe un cliente con el documento de identificación: " + identification.getIdentification_doc());
        }

        // Obtener estado activo (ID = 1)
        StateEntity stateActive = stateEntityRepository.findById(1)
                .orElseThrow(() -> new EntityNotFoundException("Estado activo no encontrado"));

        // Crear el cliente
        Client client = clientMapper.toModel(dto);
        client.setClient_type_id(clientType);
        client.setIdentification_id(identification);
        client.setState_entity_id(stateActive);

        Client savedClient = clientRepository.save(client);
        log.info("Cliente creado exitosamente con ID: {}", savedClient.getClient_id());

        return clientMapper.toDTO(savedClient);
    }

    @Transactional
    @Override
    public List<ClientResponseDTO> allList() {
        log.info("Obteniendo todos los clientes");
        List<Client> clients = clientRepository.findAll();
        return clients.stream()
                .map(clientMapper::toDTO)
                .toList();
    }

    @Transactional
    @Override
    public ClientResponseDTO readById(Long id) {
        log.info("Buscando cliente con ID: {}", id);
        Optional<Client> clientOpt = clientRepository.findById(id.intValue());

        if (clientOpt.isEmpty()) {
            log.error("Cliente no encontrado con ID: {}", id);
            throw new EntityNotFoundException("Cliente no encontrado con ID: " + id);
        }

        return clientMapper.toDTO(clientOpt.get());
    }

    @Transactional
    @Override
    public void remove(int id) {
        log.info("Eliminando (lógicamente) cliente con ID: {}", id);

        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con ID: " + id));

        // Eliminación lógica: cambiar estado a eliminado (ID = 0)
        StateEntity stateDeleted = stateEntityRepository.findById(0)
                .orElseThrow(() -> new EntityNotFoundException("Estado eliminado no encontrado"));

        client.setState_entity_id(stateDeleted);
        clientRepository.save(client);
        log.info("Cliente eliminado (lógicamente) exitosamente con ID: {}", id);
    }

    @Transactional
    @Override
    public ClientResponseDTO update(int id, ClientRequestDTO dto) {
        log.info("Actualizando cliente con ID: {}", id);

        // Validar datos del cliente
        utilityValidator.validate(dto);

        // Verificar que el cliente existe
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con ID: " + id));

        // Verificar que el tipo de cliente existe
        ClientType clientType = clientTypeRepository.findById(dto.getClient_type_id())
                .orElseThrow(() -> new EntityNotFoundException("Tipo de cliente no encontrado con ID: " + dto.getClient_type_id()));

        // Verificar que la identificación existe
        Identification identification = identificationRepository.findById(dto.getIdentification_id())
                .orElseThrow(() -> new EntityNotFoundException("Identificación no encontrada con ID: " + dto.getIdentification_id()));

        // Actualizar datos del cliente
        existingClient.setClient_name(dto.getClient_name());
        existingClient.setClient_last_name(dto.getClient_last_name());
        existingClient.setClient_type_id(clientType);
        existingClient.setIdentification_id(identification);

        Client updatedClient = clientRepository.save(existingClient);
        log.info("Cliente actualizado exitosamente con ID: {}", updatedClient.getClient_id());

        return clientMapper.toDTO(updatedClient);
    }
}
