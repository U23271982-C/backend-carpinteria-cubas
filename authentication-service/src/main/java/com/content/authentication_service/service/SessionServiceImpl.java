package com.content.authentication_service.service;

import com.content.authentication_service.dto.request.LoginUserDTO;
import com.content.authentication_service.dto.request.SessionRequestDTO;
import com.content.authentication_service.dto.response.SessionResponseDTO;
import com.content.authentication_service.mapper.SessionMapper;
import com.content.authentication_service.model.Session;
import com.content.authentication_service.model.UserEmployee;
import com.content.authentication_service.repository.SessionRepository;
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
public class SessionServiceImpl implements ServiceAbs<LoginUserDTO, SessionResponseDTO> {

    private final SessionRepository sessionRepository;
    private final SessionMapper sessionMapper;
    private final UserEmployeeServiceImpl userEmployeeServiceImpl;

    @Override
    public SessionResponseDTO create(LoginUserDTO dto) {
        UserEmployee userEmployee = userEmployeeServiceImpl.getByUserAndPassword(dto.getUsername(), dto.getPassword());
        Session session = new Session();
        session.setUuid(UUID.randomUUID());
        session.setUser_employee_id(userEmployee);
        session.setSesion_date(java.time.LocalDateTime.now());
        session.setSuccesed(true);
        Session session_save = sessionRepository.save(session);
        return sessionMapper.toDTO(session_save);
    }

    @Override
    public List<SessionResponseDTO> allList() {
        return List.of();
    }

    @Override
    public SessionResponseDTO readById(UUID uuid) {
        return null;
    }

    @Override
    public void remove(UUID uuid) {

    }

    @Override
    public SessionResponseDTO update(UUID uuid, LoginUserDTO dto) {
        return null;
    }




}
