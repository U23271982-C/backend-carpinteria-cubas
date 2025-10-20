package com.content.authentication_service.controller;

import com.content.authentication_service.dto.response.StateEntityResponseDTO;
import com.content.authentication_service.service.StateEntityServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/state-entity")
@RequiredArgsConstructor
@Slf4j
public class StateEntityController {

    private final StateEntityServiceImpl stateEntityServiceImpl;

    @GetMapping
    public ResponseEntity<List<StateEntityResponseDTO>> findAll() {
        log.info("Solicitud recibida para obtener todas las entidades estatales");
        List<StateEntityResponseDTO> states = stateEntityServiceImpl.allList();
        log.info("Se encontraron {} entidades estatales", states.size());
        return ResponseEntity.ok(states);
    }
}
