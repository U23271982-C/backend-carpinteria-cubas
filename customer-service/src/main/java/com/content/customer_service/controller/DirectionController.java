package com.content.customer_service.controller;

import com.content.customer_service.dto.request.DirectionRequestDTO;
import com.content.customer_service.dto.response.DirectionResponseDTO;
import com.content.customer_service.service.DirectionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestión de Direcciones
 * Implementa operaciones CRUD siguiendo principios RESTful
 * Incluye lógica automática para crear departamentos, provincias y distritos
 */
@RestController
@RequestMapping("/directions")
@RequiredArgsConstructor
@Slf4j
public class DirectionController {

}
