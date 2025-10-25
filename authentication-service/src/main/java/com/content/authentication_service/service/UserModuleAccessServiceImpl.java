package com.content.authentication_service.service;

import com.content.authentication_service.dto.request.UserModuleAccessRequestDTO;
import com.content.authentication_service.dto.response.UserModuleAccessResponseDTO;
import com.content.authentication_service.mapper.UserModuleAccessMapper;
import com.content.authentication_service.model.Module;
import com.content.authentication_service.model.UserEmployee;
import com.content.authentication_service.model.UserModuleAccess;
import com.content.authentication_service.repository.ModuleRepository;
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
public class UserModuleAccessServiceImpl implements ServiceAbs<UserModuleAccessRequestDTO, UserModuleAccessResponseDTO> {

    private final UserModuleAccessRepository userModuleAccessRepository;
    private final UserModuleAccessMapper userModuleAccessMapper;
    private final ModuleRepository moduleRepository;
    private final UserEmployeeRepository userEmployeeRepository;

    @Override
    public UserModuleAccessResponseDTO create(UserModuleAccessRequestDTO dto) {
        // Validar si ya existe un UserModuleAccess con el mismo module_id y user_employee_id
        if (existsByModuleIdAndUserEmployeeId(dto.getModule_uuid() ,dto.getEmployee_uuid())) {
            throw new RuntimeException("Ya existe un UserModuleAccess con el mismo module_id y user_employee_id");
        }
        Module module = moduleRepository.findByUuid(dto.getModule_uuid()).orElseThrow(()-> new RuntimeException("Module no encontrado con UUID: " + dto.getModule_uuid()));
        UserEmployee userEmployee = userEmployeeRepository.findByUuid(dto.getEmployee_uuid()).orElseThrow(()-> new RuntimeException("UserEmployee no encontrado con UUID: " + dto.getEmployee_uuid()));
        UserModuleAccess userModuleAccess = userModuleAccessMapper.toModel(dto);
        userModuleAccess.setUuid(UUID.randomUUID());
        userModuleAccess.setModule_id(module);
        userModuleAccess.setUser_employee_id(userEmployee);
        return userModuleAccessMapper.toDTO(userModuleAccessRepository.save(userModuleAccess));
    }

    @Override
    public List<UserModuleAccessResponseDTO> allList() {
        return userModuleAccessRepository.findAll()
                .stream()
                .filter( userModuleAccess -> userModuleAccess.getUser_module_access_id() != null)
                .map(userModuleAccessMapper::toDTO)
                .toList();
    }

    @Override
    public UserModuleAccessResponseDTO readById(UUID uuid) {
        return userModuleAccessMapper.toDTO(userModuleAccessRepository.findByUuid(uuid).orElseThrow(()-> new RuntimeException("UserModuleAccess no encontrado")));
    }

    @Override
    public void remove(UUID uuid) {
        UserModuleAccess userModuleAccess = userModuleAccessRepository.findByUuid(uuid).orElseThrow(()-> new RuntimeException("UserModuleAccess no encontrado"));
        userModuleAccessRepository.delete(userModuleAccess);
    }

    @Override
    public UserModuleAccessResponseDTO update(UUID uuid, UserModuleAccessRequestDTO dto) {
        UserModuleAccess existingUserModuleAccess = userModuleAccessRepository.findByUuid(uuid).orElseThrow(()-> new RuntimeException("UserModuleAccess no encontrado"));
        UserEmployee userEmployee = userEmployeeRepository.findByUuid(dto.getEmployee_uuid()).orElseThrow(()-> new RuntimeException("UserEmployee no encontrado"));
        Module module = moduleRepository.findByUuid(dto.getModule_uuid()).orElseThrow(()-> new RuntimeException("Module no encontrado"));
        if (existingUserModuleAccess == null) {
            throw new RuntimeException("UserModuleAccess no encontrado");
        }
        if (userEmployee.getState_entity_id().getStateId() == 1) {
            existingUserModuleAccess.setUser_employee_id(userEmployee);
        }
        if (module.getState_entity_id().getStateId() == 1) {
            existingUserModuleAccess.setModule_id(module);
        }
        UserModuleAccess updatedUserModuleAccess = userModuleAccessRepository.save(existingUserModuleAccess);
        return userModuleAccessMapper.toDTO(updatedUserModuleAccess);
    }

    public List<UserModuleAccessResponseDTO> getByUserEmployeeUuid(UUID userEmployeeUuid) {
        UserEmployee userEmloyee = userEmployeeRepository.findByUuid(userEmployeeUuid).orElseThrow(()-> new RuntimeException("UserEmployee no encontrado con UUID: " + userEmployeeUuid));

        return userModuleAccessRepository.findAll()
                .stream()
                .filter(uma -> uma.getUser_employee_id().equals(userEmloyee))
                .map(userModuleAccessMapper::toDTO)
                .toList();
    }

    public boolean existsByModuleIdAndUserEmployeeId(UUID moduleUuid, UUID userEmployeeUuid) {
        return userModuleAccessRepository.findAll()
                .stream()
                .anyMatch(uma -> uma.getModule_id().getUuid().equals(moduleUuid) &&
                        uma.getUser_employee_id().getUuid().equals(userEmployeeUuid));
    }
}
