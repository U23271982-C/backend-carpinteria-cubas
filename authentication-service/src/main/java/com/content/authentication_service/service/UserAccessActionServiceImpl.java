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
    private final UserModuleAccessServiceImpl userModuleAccessServiceImpl;
    private final ActionServiceImpl actionServiceImpl;

    @Override
    public UserAccessActionResponseDTO create(UserAccessActionRequestDTO dto) {
        UserAccessAction userAccessAction = userAccesActionMapper.toModel(dto);

        UUID uuid = UUID.randomUUID();
        userAccessAction.setUuid(uuid);
        // Validar si ya existe un UserAccessAction con el mismo user_module_access_id y action_id
        if (existsByUserModuleAccessIdAndActionId(userAccessAction.getUser_module_access_id().getUuid(), userAccessAction.getUser_module_access_id().getUuid())) {
            throw new RuntimeException("Ya existe un UserAccessAction con el mismo user_module_access_id y action_id");
        }

        // Asignar UserModuleAccess
        if(userAccessAction.getUser_module_access_id().getUuid() != null){
            userAccessAction.setUser_module_access_id(userModuleAccessServiceImpl.findByUUID(userAccessAction.getUser_module_access_id().getUuid()));
        }
        // Asignar Action
        if(userAccessAction.getAction_id().getUuid() != null){
            userAccessAction.setAction_id(actionServiceImpl.findByUUID(userAccessAction.getAction_id().getUuid()));
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
        return userAccesActionMapper.toDTO(findByUUID(uuid));
    }

    @Override
    public void remove(UUID uuid) {
        userAccessActionRepository.delete(findByUUID(uuid));
    }

    @Override
    public UserAccessActionResponseDTO update(UUID uuid, UserAccessActionRequestDTO dto) {
        UserAccessAction userAccessAction = findByUUID(uuid);
        if(dto.getAction_uuid() != null){
            userAccessAction.setAction_id(actionServiceImpl.findByUUID(dto.getAction_uuid()));
        }
        UserAccessAction savedUserAccessAction = userAccessActionRepository.save(userAccessAction);
        return userAccesActionMapper.toDTO(savedUserAccessAction);
    }


    public UserAccessAction findByUUID(UUID uuid){
        return userAccessActionRepository.findAll()
                .stream()
                .filter( uaa -> uaa.getUuid().equals(uuid))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No se encontro user_access_action"));
    }


    public List<UserAccessActionResponseDTO> getByUserModuleAccess(UUID userModuleAccess) {
        UserModuleAccess uma = userModuleAccessServiceImpl.findByUUID(userModuleAccess);
        return userAccessActionRepository.findAll()
                .stream()
                .filter( uaa -> uaa.getUser_module_access_id().equals(uma))
                .map(userAccesActionMapper::toDTO)
                .toList();
    }


    // Validar si ya existe un UserAccessAction con el mismo user_module_access_id y action_id
    public boolean existsByUserModuleAccessIdAndActionId(UUID userModuleAccessId, UUID actionId){
        return userAccessActionRepository.findAll()
                .stream()
                .anyMatch( uaa -> uaa.getUser_module_access_id().getUuid().equals(userModuleAccessId) &&
                        uaa.getAction_id().getUuid().equals(actionId));
    }

}
