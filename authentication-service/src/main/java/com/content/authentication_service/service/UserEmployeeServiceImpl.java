package com.content.authentication_service.service;

import com.content.authentication_service.dto.request.UserEmployeeRequestDTO;
import com.content.authentication_service.dto.response.UserEmployeeResponseDTO;
import com.content.authentication_service.mapper.UserEmployeeMapper;
import com.content.authentication_service.model.StateEntity;
import com.content.authentication_service.model.UserEmployee;
import com.content.authentication_service.model.UserEmployeePosition;
import com.content.authentication_service.model.UserModuleAccess;
import com.content.authentication_service.repository.StateEntityRepository;
import com.content.authentication_service.repository.UserEmployeePositionRepository;
import com.content.authentication_service.repository.UserEmployeeRepository;
import com.content.authentication_service.repository.UserModuleAccessRepository;
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
public class UserEmployeeServiceImpl implements ServiceAbs<UserEmployeeRequestDTO, UserEmployeeResponseDTO> {

    private final UserEmployeeMapper userEmployeeMapper;
    private final UserEmployeeRepository userEmployeeRepository;
    private final StateEntityRepository stateEntityRepository;
    private final UserEmployeePositionRepository userEmployeePositionRepository;
    private final UserModuleAccessRepository userModuleAccessRepository;


    @Override
    public UserEmployeeResponseDTO create(UserEmployeeRequestDTO dto) {
        // Convertir DTO a entidad
        UserEmployee userEmployee = userEmployeeMapper.toModel(dto);
        // Generar y asignar UUID
        UUID uuid = UUID.randomUUID();
        userEmployee.setUuid(uuid);

        if (userEmployee.getState_entity_id().getUuid() == null) {
            log.info("No se proporcionó state_entity_id, asignando estado predeterminado (activo)");
            StateEntity defaultState = stateEntityRepository.findAll()
                    .stream()
                    .filter(stateEntity -> stateEntity.getState_entity_id() == 1)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("No se encontro state_entity"));

            userEmployee.setState_entity_id(defaultState);
        }

        if (userEmployee.getUser_employee_position_id().getUuid() != null) {
            log.info("No se proporcionó user_employee_position_id");
            UserEmployeePosition userEmployeePosition = userEmployeePositionRepository.findAll()
                    .stream()
                    .filter(position -> position.getUuid().equals(userEmployee.getUser_employee_position_id().getUuid()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("No se encontro user_employee_position"));
            userEmployee.setUser_employee_position_id(userEmployeePosition);
        } else {
            throw new RuntimeException("user_employee_position_id es obligatorio");
        }

        // Guardar entidad en la base de datos
        UserEmployee savedUserEmployee = userEmployeeRepository.save(userEmployee);
        // Convertir entidad guardada a DTO y retornarlo
        return userEmployeeMapper.toDTO(savedUserEmployee);
    }

    @Override
    public List<UserEmployeeResponseDTO> allList() {
        return userEmployeeRepository.findAll()
                .stream()
                .filter(userEmployee -> userEmployee.getState_entity_id().getState_entity_id() != 3)
                .map(userEmployeeMapper::toDTO)
                .toList(); // Excluir eliminados;
    }

    @Override
    public UserEmployeeResponseDTO readById(UUID uuid) {
            UserEmployee userEmployee = userEmployeeRepository.findAll()
                    .stream()
                    .filter(userEmploye -> userEmploye.getUuid().equals(uuid) && userEmploye.getState_entity_id().getState_entity_id() != 3)
                    .findFirst()
                    .orElseThrow(()-> new RuntimeException("No se encontro state_entity"));
        return userEmployeeMapper.toDTO(userEmployee);
    }

    @Override
    public void remove(UUID uuid) {
            UserEmployee userEmployee = userEmployeeRepository.findAll()
                    .stream()
                    .filter(user -> user.getUuid().equals(uuid) && user.getState_entity_id().getState_entity_id() != 3) // Excluir eliminados
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con UUID: " + uuid));


            StateEntity deletedState = new StateEntity();
            deletedState.setState_entity_id(3); // Estado eliminado
            userEmployee.setState_entity_id(deletedState);
            List<UserModuleAccess> userModuleAccess = userModuleAccessRepository.findAll()
                    .stream()
                    .filter(uma -> uma.getUser_employee_id().equals(userEmployee))
                    .toList();
            userModuleAccessRepository.deleteAll(userModuleAccess);
            userEmployeeRepository.save(userEmployee);
    }

    @Override
    public UserEmployeeResponseDTO update(UUID uuid, UserEmployeeRequestDTO dto) {
        UserEmployee existingUserEmployee = userEmployeeRepository.findAll()
                .stream()
                .filter(user -> user.getUuid().equals(uuid) && user.getState_entity_id().getState_entity_id() != 3) // Excluir eliminados
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con UUID: " + uuid));

        if (dto.getName() != null) {
            existingUserEmployee.setUser_employee_name(dto.getName());
        }
        if (dto.getPhone() != null) {
            existingUserEmployee.setUser_employee_phone(dto.getPhone());
        }
        if (dto.getPositionUUID() != null) {
            UserEmployeePosition position = userEmployeePositionRepository.findAll()
                    .stream()
                    .filter(pos -> pos.getUuid().equals(dto.getPositionUUID()) && pos.getState_entity_id().getState_entity_id() != 3)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Posición no encontrada con UUID: " + dto.getPositionUUID()));
            existingUserEmployee.setUser_employee_position_id(position);
        }
        if (dto.getStateUUID() != null) {
            StateEntity stateEntity = stateEntityRepository.findAll()
                    .stream()
                    .filter(state -> state.getUuid().equals(dto.getStateUUID()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Estado no encontrado con UUID: " + dto.getStateUUID()));
            existingUserEmployee.setState_entity_id(stateEntity);
        }

        UserEmployee updatedUserEmployee = userEmployeeRepository.save(existingUserEmployee);
        return userEmployeeMapper.toDTO(updatedUserEmployee);
    }
}
