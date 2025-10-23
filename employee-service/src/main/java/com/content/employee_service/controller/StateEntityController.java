package com.content.employee_service.controller;

import com.content.employee_service.dto.response.StateEntityResponseDTO;
import com.content.employee_service.mapper.mapperImpl.StateEntityMapper;
import com.content.employee_service.repository.StateEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador solo para listar los estados de entidad, los pruebas.
 */
@RestController
@RequestMapping("/state-entity")
@RequiredArgsConstructor
@Slf4j
public class StateEntityController {
    private final StateEntityRepository repository;
    private final StateEntityMapper mapper;

    @GetMapping
    public List<StateEntityResponseDTO> getAll(){
        log.info("Recibida solicitud para obtener todos los estados de entidad");
        return repository.findAll().stream().map(mapper::toDTO).toList();
    }
}
