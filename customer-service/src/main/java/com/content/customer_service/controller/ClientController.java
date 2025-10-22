package com.content.customer_service.controller;

import com.content.customer_service.dto.request.ClientRequestDTO;
import com.content.customer_service.dto.response.ClientResponseDTO;
import com.content.customer_service.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestión de Clientes - USA SOLO UUIDs
 * Todas las rutas usan UUIDs como identificadores públicos
 */
@RestController
@RequestMapping("/api/v1/clients")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class ClientController {

    private final ClientService clientService;

    /**
     * Crear un nuevo cliente
     * POST /api/v1/clients
     */
    @PostMapping
    public ResponseEntity<ClientResponseDTO> createClient(@Valid @RequestBody ClientRequestDTO clientRequestDTO) {
        log.info("Request para crear cliente: {} {}", clientRequestDTO.getClientName(), clientRequestDTO.getClientLastName());
        
        ClientResponseDTO response = clientService.create(clientRequestDTO);
        
        log.info("Cliente creado exitosamente con UUID: {}", response.getUuid());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Obtener cliente por UUID (identificador público)
     * GET /api/v1/clients/{uuid}
     */
    @GetMapping("/{uuid}")
    public ResponseEntity<ClientResponseDTO> getClientByUuid(@PathVariable String uuid) {
        log.info("Request para obtener cliente con UUID: {}", uuid);
        
        ClientResponseDTO response = clientService.getByUuid(uuid);
        
        log.info("Cliente encontrado: {}", response.getClientName());
        return ResponseEntity.ok(response);
    }

    /**
     * Obtener todos los clientes activos
     * GET /api/v1/clients
     */
    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> getAllClients() {
        log.info("Request para obtener todos los clientes activos");
        
        List<ClientResponseDTO> response = clientService.getAll();
        
        log.info("Se encontraron {} clientes activos", response.size());
        return ResponseEntity.ok(response);
    }

    /**
     * Actualizar cliente por UUID
     * PUT /api/v1/clients/{uuid}
     */
    @PutMapping("/{uuid}")
    public ResponseEntity<ClientResponseDTO> updateClient(
            @PathVariable String uuid,
            @Valid @RequestBody ClientRequestDTO clientRequestDTO) {
        log.info("Request para actualizar cliente con UUID: {}", uuid);
        
        ClientResponseDTO response = clientService.update(uuid, clientRequestDTO);
        
        log.info("Cliente actualizado exitosamente: {}", response.getClientName());
        return ResponseEntity.ok(response);
    }

    /**
     * Eliminar cliente por UUID (soft delete)
     * DELETE /api/v1/clients/{uuid}
     */
    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteClient(@PathVariable String uuid) {
        log.info("Request para eliminar cliente con UUID: {}", uuid);
        
        clientService.delete(uuid);
        
        log.info("Cliente eliminado exitosamente");
        return ResponseEntity.noContent().build();
    }

    /**
     * Buscar cliente por número de identificación
     * GET /api/v1/clients/search/identification/{identificationNumber}
     */
    @GetMapping("/search/identification/{identificationNumber}")
    public ResponseEntity<ClientResponseDTO> getClientByIdentification(@PathVariable String identificationNumber) {
        log.info("Request para buscar cliente por identificación: {}", identificationNumber);

        ClientResponseDTO response = clientService.getByIdentificationNumber(identificationNumber);

        log.info("Cliente encontrado por identificación: {}", response.getClientName());
        return ResponseEntity.ok(response);
    }

    /**
     * Buscar clientes por nombre
     * GET /api/v1/clients/search/name?q={searchTerm}
     */
    @GetMapping("/search/name")
    public ResponseEntity<List<ClientResponseDTO>> searchClientsByName(@RequestParam String q) {
        log.info("Request para buscar clientes por nombre: {}", q);

        List<ClientResponseDTO> response = clientService.searchByName(q);

        log.info("Se encontraron {} clientes con el término: {}", response.size(), q);
        return ResponseEntity.ok(response);
    }
}
