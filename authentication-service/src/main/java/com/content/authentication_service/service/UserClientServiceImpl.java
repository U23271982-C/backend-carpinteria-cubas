package com.content.authentication_service.service;

import com.content.authentication_service.dto.request.UserClientRequestDTO;
import com.content.authentication_service.dto.response.UserClientResponseDTO;
import com.content.authentication_service.mapper.UserClientMapper;
import com.content.authentication_service.model.StateEntity;
import com.content.authentication_service.model.UserClient;
import com.content.authentication_service.repository.UserClientRepository;
import com.content.authentication_service.service.abstractservice.ServiceAbs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserClientServiceImpl implements ServiceAbs<UserClientRequestDTO, UserClientResponseDTO> {

    private final UserClientRepository userClientRepository;
    private final UserClientMapper userClientMapper;

    @Override
    public UserClientResponseDTO create(UserClientRequestDTO dto) {
        // Mapear DTO a entidad (el mapper ya se encarga del firebaseUid y timestamp)
        UserClient userClient = userClientMapper.toModel(dto);

        UUID uuid = UUID.randomUUID();
        userClient.setUuid(uuid);
        // Asignar estado activo (ID = 1)
        StateEntity stateEntity = new StateEntity();
        stateEntity.setState_entity_id(1);
        userClient.setState_entity_id(stateEntity);

        UserClient savedUser = userClientRepository.save(userClient);
        return userClientMapper.toDTO(savedUser);
    }

    @Override
    public UserClientResponseDTO readById(UUID uuid) {
        UserClient userClient = userClientRepository.findAll()
                .stream()
                .filter(user -> user.getUuid().equals(uuid) && user.getState_entity_id().getState_entity_id() != 3) // Excluir eliminados
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con UUID: " + uuid));
        return userClientMapper.toDTO(userClient);
    }

    @Override
    public UserClientResponseDTO update(UUID uuid, UserClientRequestDTO dto) {

        UserClient existingUser = userClientRepository.findAll()
                .stream()
                .filter(user -> user.getUuid().equals(uuid) && user.getState_entity_id().getState_entity_id() != 3) // Excluir eliminados
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con UUID: " + uuid));

        // Actualizar campos permitidos (no actualizamos firebaseUid)
        if (dto.getFullName() != null && !dto.getFullName().trim().isEmpty()) {
            existingUser.setUser_client_full_name(dto.getFullName());
        }
        if (dto.getPhone() != null && !dto.getPhone().trim().isEmpty()) {
            existingUser.setUser_client_phone(dto.getPhone());
        }
        if (dto.getAddress() != null && !dto.getAddress().trim().isEmpty()) {
            existingUser.setUser_client_address(dto.getAddress());
        }

        UserClient updatedUser = userClientRepository.save(existingUser);
        return userClientMapper.toDTO(updatedUser);
    }

    @Override
    public void remove(UUID uuid) {
        UserClient userClient = userClientRepository.findAll()
                .stream()
                .filter(user -> user.getUuid().equals(uuid) && user.getState_entity_id().getState_entity_id() != 3) // Excluir eliminados
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con UUID: " + uuid));

        StateEntity stateEntity = new StateEntity();
        stateEntity.setState_entity_id(3); //Estado Eliminado
        userClient.setState_entity_id(stateEntity);
        userClientRepository.save(userClient);
    }

    @Override
    public List<UserClientResponseDTO> allList() {
        return userClientRepository.findAll()
                .stream().filter(user -> user.getState_entity_id().getState_entity_id() != 3) // Excluir eliminados
                .map(userClientMapper::toDTO)
                .toList();
    }
}