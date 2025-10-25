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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
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
        stateEntity.setStateId(1);
        userClient.setState_entity_id(stateEntity);

        UserClient savedUser = userClientRepository.save(userClient);
        return userClientMapper.toDTO(savedUser);
    }
    @Override
    public List<UserClientResponseDTO> allList() {
        return userClientRepository.findAll()
                .stream().filter(user -> user.getState_entity_id().getStateId() != 3) // Excluir eliminados
                .map(userClientMapper::toDTO)
                .toList();
    }

    @Override
    public UserClientResponseDTO readById(UUID uuid) {
        UserClient userClient = userClientRepository.findByUuid(uuid).orElseThrow(() -> new RuntimeException("Usuario no encontrado con UUID: " + uuid));
        return userClientMapper.toDTO(userClient);
    }

    @Override
    public UserClientResponseDTO update(UUID uuid, UserClientRequestDTO dto) {
        UserClient existingUser = userClientRepository.findByUuid(uuid).orElseThrow(()-> new RuntimeException("Usuario no encontrado con UUID: " + uuid));
        if (existingUser.getState_entity_id().getStateId() == 3) {
            throw new RuntimeException("Usuario no encontrado con UUID: " + uuid);
        }
        // Actualizar campos permitidos (no actualizamos firebaseUid)
        return getUserClientResponseDTO(dto, existingUser);
    }

    @Override
    public void remove(UUID uuid) {
        UserClient userClient = userClientRepository.findByUuid(uuid).orElseThrow(() -> new RuntimeException("Usuario no encontrado con UUID: " + uuid));
        StateEntity stateEntity = new StateEntity();
        stateEntity.setStateId(3); //Estado Eliminado
        userClient.setState_entity_id(stateEntity);
        userClientRepository.save(userClient);
    }

    public UserClientResponseDTO updateByFirebaseUid(String firebaseUid, UserClientRequestDTO dto) {
        UserClient existingUser = userClientRepository.findByFireBaseUid(firebaseUid).orElseThrow(()-> new RuntimeException("Usuario no encontrado con Firebase UID: " + firebaseUid));
        // Actualizar campos permitidos (no actualizamos firebaseUid)
        return getUserClientResponseDTO(dto, existingUser);
    }
    public UserClientResponseDTO readByFirebaseUid(String firebaseUid) {
        UserClient userClient = userClientRepository.findByFireBaseUid(firebaseUid).orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + firebaseUid));
        return userClientMapper.toDTO(userClient);
    }

    private UserClientResponseDTO getUserClientResponseDTO(UserClientRequestDTO dto, UserClient existingUser) {
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
}