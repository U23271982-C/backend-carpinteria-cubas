package com.content.authentication_service.service;

import com.content.authentication_service.dto.request.UserEmployeePositionRequestDTO;
import com.content.authentication_service.dto.response.UserEmployeePositionResponseDTO;
import com.content.authentication_service.mapper.UserEmployeePositionMapper;
import com.content.authentication_service.model.StateEntity;
import com.content.authentication_service.model.UserEmployeePosition;
import com.content.authentication_service.repository.StateEntityRepository;
import com.content.authentication_service.repository.UserEmployeePositionRepository;
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
public class UserEmployeePositionServiceImpl implements ServiceAbs<UserEmployeePositionRequestDTO, UserEmployeePositionResponseDTO> {

    private final UserEmployeePositionRepository userEmployeePositionRepository;
    private final UserEmployeePositionMapper userEmployeePositionMapper;
    private final StateEntityRepository stateEntityRepository;

    @Override
    public UserEmployeePositionResponseDTO create(UserEmployeePositionRequestDTO dto) {
        if (dto.getStateEntityuuid() != null) {
            throw new RuntimeException("El State Entity no debe ser proporcionado al crear una posición de empleado");
        }
        UserEmployeePosition userEmployeePosition = userEmployeePositionMapper.toModel(dto);
        userEmployeePosition.setUuid(UUID.randomUUID());
        userEmployeePosition.setState_entity_id(stateEntityRepository.findByStateId(1).orElseThrow(() -> new RuntimeException("El State Entity no existe")));
        UserEmployeePosition savedPosition = userEmployeePositionRepository.save(userEmployeePosition);
        return userEmployeePositionMapper.toDTO(savedPosition);
    }

    @Override
    public List<UserEmployeePositionResponseDTO> allList() {
        return userEmployeePositionRepository.findAll()
                .stream()
                .filter(userEmployeePosition -> userEmployeePosition.getState_entity_id().getStateId() != 3)
                .map(userEmployeePositionMapper::toDTO)
                .toList(); // Excluir eliminados;
    }

    @Override
    public UserEmployeePositionResponseDTO readById(UUID uuid) {
        UserEmployeePosition userEmployeePosition = userEmployeePositionRepository.findByUuid(uuid).orElseThrow(() -> new RuntimeException("El usuario no existe"));
        return userEmployeePositionMapper.toDTO(userEmployeePosition);
    }

    @Override
    public void remove(UUID uuid) {
        UserEmployeePosition userEmployeePosition = userEmployeePositionRepository.findByUuid(uuid).orElseThrow(() -> new RuntimeException("El usuario no existe"));
        StateEntity deletedState = new StateEntity();
        deletedState.setStateId(3); // Estado eliminado
        userEmployeePosition.setState_entity_id(deletedState);
        userEmployeePositionRepository.save(userEmployeePosition);
    }

    @Override
    public UserEmployeePositionResponseDTO update(UUID uuid, UserEmployeePositionRequestDTO dto) {
        UserEmployeePosition existingPosition = userEmployeePositionRepository.findByUuid(uuid).orElseThrow(() -> new RuntimeException("El usuario no existe"));
        // Actualizar campos permitidos
            if (dto.getPositionName() != null && !dto.getPositionName().trim().isEmpty()) {
                existingPosition.setPosition_name(dto.getPositionName());
            }
            if (dto.getPositionDescription() != null && !dto.getPositionDescription().trim().isEmpty()) {
                existingPosition.setPosition_description(dto.getPositionDescription());
            }
            if (dto.getStateEntityuuid() != null) {
                StateEntity stateEntityNew = stateEntityRepository.findByUuid(dto.getStateEntityuuid()).orElseThrow(() -> new RuntimeException("El State Entity no existe"));
                existingPosition.setState_entity_id(stateEntityNew);
            }
            UserEmployeePosition updatedPosition = userEmployeePositionRepository.save(existingPosition);
            return userEmployeePositionMapper.toDTO(updatedPosition);
    }
}
