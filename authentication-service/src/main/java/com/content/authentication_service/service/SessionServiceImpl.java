package com.content.authentication_service.service;

import com.content.authentication_service.dto.request.SessionRequestDTO;
import com.content.authentication_service.dto.response.SessionResponseDTO;
import com.content.authentication_service.mapper.SessionMapper;
import com.content.authentication_service.model.Session;
import com.content.authentication_service.model.UserEmployee;
import com.content.authentication_service.repository.SessionRepository;
import com.content.authentication_service.repository.UserEmployeeRepository;
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
public class SessionServiceImpl implements ServiceAbs<SessionRequestDTO, SessionResponseDTO> {

    private final SessionRepository sessionRepository;
    private final UserEmployeeRepository userEmployeeRepository;
    private final SessionMapper sessionMapper;

    @Override
    public SessionResponseDTO create(SessionRequestDTO dto) {
        String username = dto.getUsername();
        String password = dto.getPassword();

        UserEmployee userEmployee = userEmployeeRepository.findAll()
                .stream()
                .filter(user -> user.getUser_employee_name().equals(username) && user.getPassword().equals(password))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));
        if (userEmployee == null) {
            throw new RuntimeException("User not found");
        } else {
            Session session = new Session();
            session.setUuid(UUID.randomUUID());
            session.setUser_employee_id(userEmployee);
            session.setSesion_date(java.time.LocalDateTime.now());
            session.setSuccesed(true);
            Session session_save = sessionRepository.save(session);
            return sessionMapper.toDTO(session_save);
        }
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
    public SessionResponseDTO update(UUID uuid, SessionRequestDTO dto) {
        return null;
    }
}
