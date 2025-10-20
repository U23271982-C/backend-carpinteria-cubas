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
        UserModuleAccess userModuleAccess = userModuleAccessMapper.toModel(dto);

        UUID uuid = UUID.randomUUID();
        userModuleAccess.setUuid(uuid);
        UserModuleAccess exist = userModuleAccessRepository.findAll()
                .stream()
                .filter( uma -> uma.getModule_id().getUuid().equals(userModuleAccess.getModule_id().getUuid()) &&
                        uma.getUser_employee_id().getUuid().equals(userModuleAccess.getUser_employee_id().getUuid()))
                .findFirst()
                .orElse(null);
        // Validar si ya existe un UserModuleAccess con el mismo module_id y user_employee_id
        if (exist != null){
            throw new RuntimeException("Ya existe un UserModuleAccess con el mismo module_id y user_employee_id");
        }



        // Asignar Module
        if(userModuleAccess.getModule_id().getUuid() != null && !userModuleAccess.getModule_id().getUuid().toString().isEmpty()){
            Module module = moduleRepository.findAll()
                    .stream()
                    .filter(mod -> mod.getUuid().equals(userModuleAccess.getModule_id().getUuid()) && mod.getState_entity_id().getState_entity_id() == 1)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("No se encontro module"));
            userModuleAccess.setModule_id(module);
        }
        // Asignar UserEmployee
        if (userModuleAccess.getUser_employee_id().getUuid() != null && !userModuleAccess.getUser_employee_id().getUuid().toString().isEmpty()) {
            UserEmployee userEmployee = userEmployeeRepository.findAll()
                    .stream()
                    .filter(ue -> ue.getUuid().equals(userModuleAccess.getUser_employee_id().getUuid()) && ue.getState_entity_id().getState_entity_id() == 1)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("No se encontro user_employee"));
            userModuleAccess.setUser_employee_id(userEmployee);
        }

        UserModuleAccess savedUserModuleAccess = userModuleAccessRepository.save(userModuleAccess);
        return userModuleAccessMapper.toDTO(savedUserModuleAccess);
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
        return userModuleAccessRepository.findAll()
                .stream()
                .filter( userModuleAccess -> userModuleAccess.getUuid().equals(uuid))
                .map(userModuleAccessMapper::toDTO)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No se encontro user_module_access"));
    }

    @Override
    public void remove(UUID uuid) {
        UserModuleAccess userModuleAccess = userModuleAccessRepository.findAll()
                .stream()
                .filter( uma -> uma.getUuid().equals(uuid))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No se encontro user_module_access"));
        userModuleAccessRepository.delete(userModuleAccess);
    }

    @Override
    public UserModuleAccessResponseDTO update(UUID uuid, UserModuleAccessRequestDTO dto) {
        UserModuleAccess existingUserModuleAccess = userModuleAccessRepository.findAll()
                .stream()
                .filter( uma -> uma.getUuid().equals(uuid))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No se encontro user_module_access"));

        if (dto.getModule_uuid() != null) {
            Module module = moduleRepository.findAll()
                    .stream()
                    .filter(mod -> mod.getUuid().equals(dto.getModule_uuid()) && mod.getState_entity_id().getState_entity_id() == 1)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("No se encontro module"));
            existingUserModuleAccess.setModule_id(module);
        }
        UserModuleAccess updatedUserModuleAccess = userModuleAccessRepository.save(existingUserModuleAccess);
        return userModuleAccessMapper.toDTO(updatedUserModuleAccess);
    }

    public List<UserModuleAccessResponseDTO> getByUserEmployeeUuid(UUID userEmployeeUuid) {
        UserEmployee userEmloyee = userEmployeeRepository .findAll()
                .stream()
                .filter(ue -> ue.getUuid().equals(userEmployeeUuid))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No se encontro user_employee"));

        return userModuleAccessRepository.findAll()
                .stream()
                .filter(uma -> uma.getUser_employee_id().equals(userEmloyee))
                .map(userModuleAccessMapper::toDTO)
                .toList();
    }
}
