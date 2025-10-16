package com.content.authentication_service.service;

import com.content.authentication_service.dto.request.StateEntityRequestDTO;
import com.content.authentication_service.dto.response.StateEntityResponseDTO;
import com.content.authentication_service.mapper.StateEntityMapper;
import com.content.authentication_service.repository.StateEntityRepository;
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
public class StateEntityServiceImpl implements ServiceAbs<StateEntityRequestDTO, StateEntityResponseDTO> {

    private final StateEntityRepository stateEntityRepository;
    private final StateEntityMapper stateEntityMapper;

    @Override
    public StateEntityResponseDTO create(StateEntityRequestDTO dto) {
        return null;
    }

    @Override
    public List<StateEntityResponseDTO> allList() {
        return stateEntityRepository. findAll()
                .stream()
                .map(stateEntityMapper::toDTO)
                .toList();
    }

    @Override
    public StateEntityResponseDTO readById(UUID uuid) {
        return null;
    }

    @Override
    public void remove(UUID uuid) {

    }

    @Override
    public StateEntityResponseDTO update(UUID uuid, StateEntityRequestDTO dto) {
        return null;
    }
}
