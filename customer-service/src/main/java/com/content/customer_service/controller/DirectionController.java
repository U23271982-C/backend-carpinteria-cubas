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

    private final DirectionService directionService;

    /**
     * Crear una nueva dirección
     * El sistema creará automáticamente departamento, provincia y distrito si no existen
     * @param directionRequestDTO Datos de la dirección a crear
     * @return Dirección creada con código 201
     */
    @PostMapping
    public ResponseEntity<DirectionResponseDTO> createDirection(@Valid @RequestBody DirectionRequestDTO directionRequestDTO) {
        log.info("Recibida solicitud para crear dirección para cliente UUID: {}",
                directionRequestDTO.getClientUuid());

        DirectionResponseDTO createdDirection = directionService.create(directionRequestDTO);

        log.info("Dirección creada exitosamente con UUID: {}", createdDirection.getUuid());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDirection);
    }

    /**
     * Obtener todas las direcciones
     * @return Lista de todas las direcciones
     */
    @GetMapping
    public ResponseEntity<List<DirectionResponseDTO>> getAllDirections() {
        log.info("Recibida solicitud para obtener todas las direcciones");

        List<DirectionResponseDTO> directions = directionService.getAll();

        log.info("Se encontraron {} direcciones", directions.size());
        return ResponseEntity.ok(directions);
    }

    /**
     * Obtener una dirección por su ID
     * @param id ID de la dirección
     * @return Dirección encontrada
     */
    @GetMapping("/{id}")
    public ResponseEntity<DirectionResponseDTO> getDirectionById(@PathVariable String id) {
        log.info("Recibida solicitud para obtener dirección con UUID: {}", id);

        DirectionResponseDTO direction = directionService.getByUuid(id);

        log.info("Dirección encontrada con UUID: {}", direction.getUuid());
        return ResponseEntity.ok(direction);
    }

    /**
     * Actualizar una dirección existente
     * El sistema actualizará o creará departamento, provincia y distrito según sea necesario
     * @param id UUID de la dirección a actualizar
     * @param directionRequestDTO Nuevos datos de la dirección
     * @return Dirección actualizada
     */
    @PutMapping("/{id}")
    public ResponseEntity<DirectionResponseDTO> updateDirection(
            @PathVariable String id,
            @Valid @RequestBody DirectionRequestDTO directionRequestDTO) {

        log.info("Recibida solicitud para actualizar dirección con UUID: {}", id);

        DirectionResponseDTO updatedDirection = directionService.update(id, directionRequestDTO);

        log.info("Dirección actualizada exitosamente con UUID: {}", updatedDirection.getUuid());
        return ResponseEntity.ok(updatedDirection);
    }

    /**
     * Eliminar una dirección (eliminación lógica)
     * @param id UUID de la dirección a eliminar
     * @return Respuesta sin contenido (204)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDirection(@PathVariable String id) {
        log.info("Recibida solicitud para eliminar dirección con UUID: {}", id);

        directionService.delete(id);

        log.info("Dirección eliminada exitosamente con UUID: {}", id);
        return ResponseEntity.noContent().build();
    }
}
