package com.content.authentication_service.service;

import com.content.authentication_service.dto.request.LoginUserRequestDTO;
import com.content.authentication_service.dto.response.SessionResponseDTO;
import com.content.authentication_service.mapper.SessionMapper;
import com.content.authentication_service.model.Session;
import com.content.authentication_service.model.UserEmployee;
import com.content.authentication_service.repository.SessionRepository;
import com.content.authentication_service.repository.UserEmployeeRepository;
import com.content.authentication_service.service.abstractservice.ServiceAbs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class SessionServiceImpl implements ServiceAbs<LoginUserRequestDTO, SessionResponseDTO> {

    private final SessionRepository sessionRepository;
    private final UserEmployeeRepository userEmployeeRepository;
    private final SessionMapper sessionMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public SessionResponseDTO create(LoginUserRequestDTO dto) {
        UserEmployee userEmployee = userEmployeeRepository.findByUsername(dto.getUsername()).orElseThrow(()-> new RuntimeException("Usuario no encontrado"));
        if (userEmployee.getState_entity_id().getStateId() == 1) {
            throw new RuntimeException("Usuario inactivo");
        }
        if (!passwordEncoder.matches(dto.getPassword(), userEmployee.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }
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
    public SessionResponseDTO update(UUID uuid, LoginUserRequestDTO dto) {
        return null;
    }
}
