package com.content.authentication_service.service;

import com.content.authentication_service.dto.request.UserModuleAccessRequestDTO;
import com.content.authentication_service.dto.response.UserModuleAccessResponseDTO;
import com.content.authentication_service.mapper.UserModuleAccessMapper;
import com.content.authentication_service.model.UserEmployee;
import com.content.authentication_service.model.UserModuleAccess;
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
    private final UserEmployeeServiceImpl userEmployeeServiceImpl;
    private final ModuleServiceImpl moduleServiceImpl;

    @Override
    public UserModuleAccessResponseDTO create(UserModuleAccessRequestDTO dto) {
        UserModuleAccess userModuleAccess = userModuleAccessMapper.toModel(dto);

        UUID uuid = UUID.randomUUID();
        userModuleAccess.setUuid(uuid);

        // Validar si ya existe un UserModuleAccess con el mismo module_id y user_employee_id
        if (existsByModuleIdAndUserEmployeeId(userModuleAccess.getModule_id().getUuid() ,userModuleAccess.getUser_employee_id().getUuid())) {
            throw new RuntimeException("Ya existe un UserModuleAccess con el mismo module_id y user_employee_id");
        }
        // Asignar Module
        if(userModuleAccess.getModule_id().getUuid() != null && !userModuleAccess.getModule_id().getUuid().toString().isEmpty()){
            userModuleAccess.setModule_id(moduleServiceImpl.findByUUIDActive(userModuleAccess.getModule_id().getUuid()));
        }
        // Asignar UserEmployee
        if (userModuleAccess.getUser_employee_id().getUuid() != null && !userModuleAccess.getUser_employee_id().getUuid().toString().isEmpty()) {
            userModuleAccess.setUser_employee_id(userEmployeeServiceImpl.findByUuid(userModuleAccess.getUser_employee_id().getUuid()));
        }
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
        return userModuleAccessMapper.toDTO(findByUUID(uuid));
    }

    @Override
    public void remove(UUID uuid) {
        UserModuleAccess userModuleAccess = findByUUID(uuid);
        userModuleAccessRepository.delete(userModuleAccess);
    }

    @Override
    public UserModuleAccessResponseDTO update(UUID uuid, UserModuleAccessRequestDTO dto) {
        UserModuleAccess existingUserModuleAccess = findByUUID(uuid);
        if (dto.getModule_uuid() != null) {
            existingUserModuleAccess.setModule_id(moduleServiceImpl.findByUUIDActive(dto.getModule_uuid()));
        }
        UserModuleAccess updatedUserModuleAccess = userModuleAccessRepository.save(existingUserModuleAccess);
        return userModuleAccessMapper.toDTO(updatedUserModuleAccess);
    }


    public UserModuleAccess findByUUID(UUID uuid) {
        return userModuleAccessRepository.findAll()
                .stream()
                .filter( uma -> uma.getUuid().equals(uuid))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No se encontro user_module_access"));
    }

    public boolean existsByModuleIdAndUserEmployeeId(UUID moduleUuid, UUID userEmployeeUuid) {
        return userModuleAccessRepository.findAll()
                .stream()
                .anyMatch(uma -> uma.getModule_id().getUuid().equals(moduleUuid) &&
                        uma.getUser_employee_id().getUuid().equals(userEmployeeUuid));
    }

    public List<UserModuleAccessResponseDTO> getByUserEmployeeUuid(UUID userEmployeeUuid) {
        UserEmployee userEmloyee = userEmployeeServiceImpl.findByUuid(userEmployeeUuid);

        return userModuleAccessRepository.findAll()
                .stream()
                .filter(uma -> uma.getUser_employee_id().equals(userEmloyee))
                .map(userModuleAccessMapper::toDTO)
                .toList();
    }

    public void removeByUserEmployeeUuid(UUID userEmployeeUuid) {
        UserEmployee userEmployee = userEmployeeServiceImpl.findByUuid(userEmployeeUuid);
        List<UserModuleAccess> userModuleAccessList = userModuleAccessRepository.findAll()
                .stream()
                .filter(uma -> uma.getUser_employee_id().equals(userEmployee))
                .toList();
        userModuleAccessRepository.deleteAll(userModuleAccessList);
    }
}
