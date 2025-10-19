package com.content.authentication_service.service;

import com.content.authentication_service.dto.request.UserAccessActionRequestDTO;
import com.content.authentication_service.dto.response.UserAccessActionResponseDTO;
import com.content.authentication_service.mapper.UserAccesActionMapper;
import com.content.authentication_service.model.Action;
import com.content.authentication_service.model.UserAccessAction;
import com.content.authentication_service.model.UserModuleAccess;
import com.content.authentication_service.repository.ActionRepository;
import com.content.authentication_service.repository.UserAccessActionRepository;
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
public class UserAccessActionServiceImpl implements ServiceAbs<UserAccessActionRequestDTO, UserAccessActionResponseDTO> {

    private final UserAccessActionRepository userAccessActionRepository;
    private final UserAccesActionMapper userAccesActionMapper;
    private final UserModuleAccessRepository userModuleAccessRepository;
    private final ActionRepository actionRepository;

    @Override
    public UserAccessActionResponseDTO create(UserAccessActionRequestDTO dto) {
        UserAccessAction userAccessAction = userAccesActionMapper.toModel(dto);

        UUID uuid = UUID.randomUUID();
        userAccessAction.setUuid(uuid);

        if(userAccessAction.getUser_module_access_id().getUuid() != null){
            // Logic to fetch and set UserModuleAccess entity
            UserModuleAccess userModuleAccess = userModuleAccessRepository.findAll()
                    .stream()
                    .filter(uma -> uma.getUuid().equals(userAccessAction.getUser_module_access_id().getUuid()) && uma.getUser_module_access_id() != null)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("No se encontro user_module_access"));
            userAccessAction.setUser_module_access_id(userModuleAccess);

        }
        if(userAccessAction.getAction_id().getUuid() != null){
            Action action = actionRepository.findAll()
                    .stream()
                    .filter(act -> act.getUuid().equals(userAccessAction.getAction_id().getUuid()) && act.getState_entity_id().getState_entity_id() == 1)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("No se encontro action"));
            userAccessAction.setAction_id(action);
        }
        UserAccessAction savedUserAccessAction = userAccessActionRepository.save(userAccessAction);
        return userAccesActionMapper.toDTO(savedUserAccessAction);
    }

    @Override
    public List<UserAccessActionResponseDTO> allList() {
        return userAccessActionRepository.findAll()
                .stream()
                .filter( userAccessAction -> userAccessAction.getUser_access_action_id() != null)
                .map(userAccesActionMapper::toDTO)
                .toList();
    }

    @Override
    public UserAccessActionResponseDTO readById(UUID uuid) {
        return userAccessActionRepository.findAll()
                .stream()
                .filter( userAccessAction -> userAccessAction.getUuid().equals(uuid))
                .map(userAccesActionMapper::toDTO)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No se encontro user_access_action"));
    }

    @Override
    public void remove(UUID uuid) {
        UserAccessAction userAccessAction = userAccessActionRepository.findAll()
                .stream()
                .filter( userAccessAction1 -> userAccessAction1.getUuid().equals(uuid))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No se encontro user_access_action"));
        userAccessActionRepository.delete(userAccessAction);
    }

    @Override
    public UserAccessActionResponseDTO update(UUID uuid, UserAccessActionRequestDTO dto) {
        UserAccessAction userAccessAction = userAccessActionRepository.findAll()
                .stream()
                .filter( uaa -> uaa.getUuid().equals(uuid))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No se encontro user_access_action"));
        if(dto.getAction_uuid() != null){
            Action action = actionRepository.findAll()
                    .stream()
                    .filter(act -> act.getUuid().equals(dto.getAction_uuid()) && act.getState_entity_id().getState_entity_id() == 1)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("No se encontro action"));
            userAccessAction.setAction_id(action);
        }
        UserAccessAction savedUserAccessAction = userAccessActionRepository.save(userAccessAction);
        return userAccesActionMapper.toDTO(savedUserAccessAction);
    }

    public List<UserAccessActionResponseDTO> getByUserModuleAccess(UUID userModuleAccess) {
        UserModuleAccess uma = userModuleAccessRepository.findAll()
                .stream()
                .filter( umac -> umac.getUuid().equals(userModuleAccess))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No se encontro user_module_access"));
        return userAccessActionRepository.findAll()
                .stream()
                .filter( uaa -> uaa.getUser_module_access_id().equals(uma))
                .map(userAccesActionMapper::toDTO)
                .toList();
        }
}
