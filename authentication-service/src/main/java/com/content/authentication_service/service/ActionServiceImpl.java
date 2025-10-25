package com.content.authentication_service.service;

import com.content.authentication_service.dto.request.ActionRequestDTO;
import com.content.authentication_service.dto.response.ActionResponseDTO;
import com.content.authentication_service.mapper.ActionMapper;
import com.content.authentication_service.repository.ActionRepository;
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
public class ActionServiceImpl implements ServiceAbs<ActionRequestDTO, ActionResponseDTO> {

    private final ActionRepository actionRepository;
    private final ActionMapper actionMapper;

    @Override
    public ActionResponseDTO create(ActionRequestDTO dto) {
        return null;
    }

    @Override
    public List<ActionResponseDTO> allList() {
        return actionRepository.findAll()
                .stream()
                .filter(action -> action.getState_entity_id().getStateId() != 3)
                .map(actionMapper::toDTO)
                .toList(); // Excluir eliminados;
    }

    @Override
    public ActionResponseDTO readById(UUID uuid) {
        return null;
    }

    @Override
    public void remove(UUID uuid) {

    }

    @Override
    public ActionResponseDTO update(UUID uuid, ActionRequestDTO dto) {
        return null;
    }

}
