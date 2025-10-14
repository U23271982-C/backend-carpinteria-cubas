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

        // Asignar estado activo (ID = 1)
        StateEntity stateEntity = new StateEntity();
        stateEntity.setState_entity_id(1);
        userClient.setState_entity_id(stateEntity);

        UserClient savedUser = userClientRepository.save(userClient);
        return userClientMapper.toDTO(savedUser);
    }

    @Override
    public UserClientResponseDTO readById(Long id) {
        UserClient userClient = userClientRepository.findById(id.intValue())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
        return userClientMapper.toDTO(userClient);
    }

    @Override
    public UserClientResponseDTO update(int id, UserClientRequestDTO dto) {
        UserClient existingUser = userClientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        // Actualizar campos permitidos (no actualizamos firebaseUid)
        existingUser.setUser_client_full_name(dto.getFullName());
        existingUser.setUser_client_phone(dto.getPhone());
        existingUser.setUser_client_address(dto.getAddress());

        UserClient updatedUser = userClientRepository.save(existingUser);
        return userClientMapper.toDTO(updatedUser);
    }

    @Override
    public void remove(int id) {
        UserClient userClient = userClientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
        userClientRepository.delete(userClient);
    }

    @Override
    public List<UserClientResponseDTO> allList() {
        return userClientRepository.findAll()
                .stream()
                .map(userClientMapper::toDTO)
                .toList();
    }
}