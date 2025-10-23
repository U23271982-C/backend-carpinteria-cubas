package com.content.authentication_service.service;

import com.content.authentication_service.dto.request.UserEmployeePositionRequestDTO;
import com.content.authentication_service.dto.response.UserEmployeePositionResponseDTO;
import com.content.authentication_service.mapper.UserEmployeePositionMapper;
import com.content.authentication_service.model.StateEntity;
import com.content.authentication_service.model.UserEmployeePosition;
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
    private final StateEntityServiceImpl stateEntityServiceImpl;

    @Override
    public UserEmployeePositionResponseDTO create(UserEmployeePositionRequestDTO dto) {
        UserEmployeePosition userEmployeePosition = userEmployeePositionMapper.toModel(dto);

        UUID uuid = UUID.randomUUID();
        userEmployeePosition.setUuid(uuid);

        if (userEmployeePosition.getState_entity_id().getUuid() == null) {
            log.info("No se proporcionó state_entity_id, asignando estado predeterminado (activo)");
            StateEntity defaultState = stateEntityServiceImpl.getStateActive();
            userEmployeePosition.setState_entity_id(defaultState);
        }

        UserEmployeePosition savedPosition = userEmployeePositionRepository.save(userEmployeePosition);
        return userEmployeePositionMapper.toDTO(savedPosition);
    }

    @Override
    public List<UserEmployeePositionResponseDTO> allList() {
        return userEmployeePositionRepository.findAll()
                .stream()
                .filter(userEmployeePosition -> userEmployeePosition.getState_entity_id().getState_entity_id() != 3)
                .map(userEmployeePositionMapper::toDTO)
                .toList(); // Excluir eliminados;
    }

    @Override
    public UserEmployeePositionResponseDTO readById(UUID uuid) {
        UserEmployeePosition userEmployeePosition = getByUUID(uuid);
        return userEmployeePositionMapper.toDTO(userEmployeePosition);
    }

    @Override
    public void remove(UUID uuid) {
        UserEmployeePosition userEmployeePosition = getByUUID(uuid);

        StateEntity deletedState = new StateEntity();
        deletedState.setState_entity_id(3); // Estado eliminado
        userEmployeePosition.setState_entity_id(deletedState);
        userEmployeePositionRepository.save(userEmployeePosition);
    }

    @Override
    public UserEmployeePositionResponseDTO update(UUID uuid, UserEmployeePositionRequestDTO dto) {
        UserEmployeePosition existingPosition = getByUUID(uuid);
        // Actualizar campos permitidos

            if (dto.getPositionName() != null && !dto.getPositionName().trim().isEmpty()) {
                existingPosition.setPosition_name(dto.getPositionName());
            }
            if (dto.getPositionDescription() != null && !dto.getPositionDescription().trim().isEmpty()) {
                existingPosition.setPosition_description(dto.getPositionDescription());
            }
            if (dto.getStateEntityuuid() != null) {
                StateEntity stateEntityNew = stateEntityServiceImpl.getByUUID(dto.getStateEntityuuid());
                existingPosition.setState_entity_id(stateEntityNew);
            }

            UserEmployeePosition updatedPosition = userEmployeePositionRepository.save(existingPosition);
            return userEmployeePositionMapper.toDTO(updatedPosition);
    }

    public UserEmployeePosition getByUUID(UUID uuid){
        return userEmployeePositionRepository.findAll()
                .stream()
                .filter(userEmployeePosition -> userEmployeePosition.getUuid().equals(uuid) && userEmployeePosition.getState_entity_id().getState_entity_id() != 3) // Excluir eliminados
                .findFirst()
                .orElseThrow(()-> new RuntimeException("UserEmployeePosition not found"));
    }
    public UserEmployeePosition getByUUIDActive(UUID uuid){
        return userEmployeePositionRepository.findAll()
                .stream()
                .filter(userEmployeePosition -> userEmployeePosition.getUuid().equals(uuid) && userEmployeePosition.getState_entity_id().getState_entity_id() == 1) // Excluir eliminados
                .findFirst()
                .orElseThrow(()-> new RuntimeException("UserEmployeePosition not found"));
    }
}
