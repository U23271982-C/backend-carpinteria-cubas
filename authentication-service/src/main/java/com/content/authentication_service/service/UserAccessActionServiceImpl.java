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
    private final UserModuleAccessRepository usermoduleAccessRepository;
    private final ActionRepository actionRepository;
    private final UserAccesActionMapper userAccesActionMapper;

    @Override
    public UserAccessActionResponseDTO create(UserAccessActionRequestDTO dto) {
        // Validar si ya existe un UserAccessAction con el mismo user_module_access_id y action_id
        UserModuleAccess usermoduleAccess = usermoduleAccessRepository.findByUuid(dto.getUser_module_access_uuid()).orElseThrow(()-> new RuntimeException("No se encontro UserModuleAccess"));
        Action action = actionRepository.findByUuid(dto.getAction_uuid()).orElseThrow(()-> new RuntimeException("No se encontro Action"));
        if (userAccessActionRepository.findByUmaIdAndActionId(usermoduleAccess,action).isPresent()) {
            throw new RuntimeException("Ya existe un UserAccessAction con el mismo user_module_access_id y action_id");
        }
        UserAccessAction userAccessAction = userAccesActionMapper.toModel(dto);
        userAccessAction.setUuid(UUID.randomUUID());
        userAccessAction.setUmaId(usermoduleAccess);
        userAccessAction.setActionId(action);
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
        return userAccesActionMapper.toDTO(userAccessActionRepository.findByUuid(uuid).orElseThrow(()-> new RuntimeException("No se encontro user_access_action")));
    }

    @Override
    public void remove(UUID uuid) {
        userAccessActionRepository.delete(userAccessActionRepository.findByUuid(uuid).orElseThrow(()-> new RuntimeException("No se encontro user_access_action")));
    }

    @Override
    public UserAccessActionResponseDTO update(UUID uuid, UserAccessActionRequestDTO dto) {
        UserAccessAction userAccessAction = userAccessActionRepository.findByUuid(uuid).orElseThrow(()-> new RuntimeException("No se encontro user_access_action"));
        if(dto.getAction_uuid() != null){
            userAccessAction.setActionId(actionRepository.findByUuid(dto.getAction_uuid()).orElseThrow(()-> new RuntimeException("No se encontro user_access_action")));
        }
        UserAccessAction savedUserAccessAction = userAccessActionRepository.save(userAccessAction);
        return userAccesActionMapper.toDTO(savedUserAccessAction);
    }


    public List<UserAccessActionResponseDTO> getByUserModuleAccess(UUID userModuleAccess) {
        UserModuleAccess uma = usermoduleAccessRepository.findByUuid(userModuleAccess).orElseThrow(()-> new RuntimeException("No se encontro UserModuleAccess"));
        return userAccessActionRepository.findAll()
                .stream()
                .filter( uaa -> uaa.getUmaId().equals(uma))
                .map(userAccesActionMapper::toDTO)
                .toList();
    }

}
