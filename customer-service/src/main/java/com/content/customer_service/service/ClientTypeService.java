package com.content.customer_service.service;

import com.content.customer_service.dto.request.ClientTypeRequestDTO;
import com.content.customer_service.dto.response.ClientTypeResponseDTO;
import com.content.customer_service.exception.EServiceLayer;
import com.content.customer_service.mapper.mapperImpl.ClientTypeMapper;
import com.content.customer_service.model.ClientType;
import com.content.customer_service.model.StateEntity;
import com.content.customer_service.repository.ClientTypeRepository;
import com.content.customer_service.service.abstractService.ServiceAbs;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientTypeService implements ServiceAbs<ClientTypeRequestDTO, ClientTypeResponseDTO> {

    private final ClientTypeRepository clientTypeRepository;
    private final ClientTypeMapper clientTypeMapper;

    @Transactional
    @Override
    public ClientTypeResponseDTO create(ClientTypeRequestDTO dto) {
        log.info("ClientTypeService.create()");
        ClientType model = clientTypeMapper.toModel(dto);
        model.setState_entity_id(StateEntity.builder().state_entity_id(1).build());
        model = clientTypeRepository.save(model);
        return clientTypeMapper.toDTO(model);
    }

    @Transactional
    @Override
    public List<ClientTypeResponseDTO> allList() {
        log.info("ClientTypeService.allList()");
        return clientTypeRepository.findAll().stream()
                .filter(clientType -> clientType.getState_entity_id().getState_entity_id() != 3)
                .map(clientTypeMapper::toDTO)
                .toList();
    }

    @Transactional
    @Override
    public ClientTypeResponseDTO readByUUID(UUID uuid) {
        log.info("ClientTypeService.readByUUID()");
        ClientType model = searchEntityByUUID(uuid);
        return clientTypeMapper.toDTO(model);
    }

    @Transactional
    @Override
    public void remove(UUID uuid) {
        log.info("ClientTypeService.remove()");
        ClientType model_existente = searchEntityByUUID(uuid);
        model_existente.setState_entity_id(StateEntity.builder().state_entity_id(3).build());
        clientTypeRepository.save(model_existente);
    }

    @Transactional
    @Override
    public ClientTypeResponseDTO updateByUUID(UUID uuid, ClientTypeRequestDTO dto) {
        log.info("ClientTypeService.updateByUUID()");
        ClientType model_exiting = searchEntityByUUID(uuid);
        clientTypeMapper.updateFromDto(dto, model_exiting);
        ClientType model_save = clientTypeRepository.save(model_exiting);
        return clientTypeMapper.toDTO(model_save);
    }

    private ClientType searchEntityByUUID(UUID uuid) {
        log.info("ClientTypeService.searchEntityByUUID()");
        return clientTypeRepository.findByUuid(uuid)
                .orElseThrow(() -> new EServiceLayer(String.format("Tipo de cliente no encontrado con UUID: " + uuid)));
    }

}
