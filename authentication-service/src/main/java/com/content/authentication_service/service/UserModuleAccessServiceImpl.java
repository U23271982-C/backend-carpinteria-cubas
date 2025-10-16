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

        if(userModuleAccess.getModule_id().getUuid() != null){
            Module module = moduleRepository.findAll()
                    .stream()
                    .filter(mod -> mod.getUuid().equals(userModuleAccess.getModule_id().getUuid()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("No se encontro module"));
            userModuleAccess.setModule_id(module);
        }
        if (userModuleAccess.getUser_employee_id().getUuid() != null){
            UserEmployee userEmployee = userEmployeeRepository.findAll()
                    .stream()
                    .filter(ue -> ue.getUuid().equals(userModuleAccess.getUser_employee_id().getUuid()))
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
        return null;
    }

    @Override
    public void remove(UUID uuid) {

    }

    @Override
    public UserModuleAccessResponseDTO update(UUID uuid, UserModuleAccessRequestDTO dto) {
        return null;
    }
}
