package com.content.authentication_service.controller;

import com.content.authentication_service.dto.request.SessionRequestDTO;
import com.content.authentication_service.dto.response.SessionResponseDTO;
import com.content.authentication_service.service.SessionServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
@RequiredArgsConstructor
@Slf4j
public class SessionController {

    private final SessionServiceImpl sessionServiceImpl;

    @PostMapping
    public ResponseEntity<SessionResponseDTO> creat(@Valid @RequestBody SessionRequestDTO dto) {
        SessionResponseDTO userEmployee = sessionServiceImpl.create(dto);
        return ResponseEntity.ok(userEmployee);
    }
}
