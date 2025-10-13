package com.content.customer_service.controller;

import com.content.customer_service.dto.request.ClientRequestDTO;
import com.content.customer_service.dto.response.ClientResponseDTO;
import com.content.customer_service.service.ClientServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestión de Clientes
 * Implementa operaciones CRUD siguiendo principios RESTful
 */
@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
@Slf4j
public class ClientController {

    private final ClientServiceImpl clientService;

    /**
     * Crear un nuevo cliente
     * @param clientRequestDTO Datos del cliente a crear
     * @return Cliente creado con código 201
     */
    @PostMapping
    public ResponseEntity<ClientResponseDTO> createClient(@Valid @RequestBody ClientRequestDTO clientRequestDTO) {
        log.info("Recibida solicitud para crear cliente: {} {}",
                clientRequestDTO.getClient_name(), clientRequestDTO.getClient_last_name());

        ClientResponseDTO createdClient = clientService.create(clientRequestDTO);

        log.info("Cliente creado exitosamente con ID: {}", createdClient.getClient_id());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);
    }

    /**
     * Obtener todos los clientes
     * @return Lista de todos los clientes
     */
    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> getAllClients() {
        log.info("Recibida solicitud para obtener todos los clientes");

        List<ClientResponseDTO> clients = clientService.allList();

        log.info("Se encontraron {} clientes", clients.size());
        return ResponseEntity.ok(clients);
    }

    /**
     * Obtener un cliente por su ID
     * @param id ID del cliente
     * @return Cliente encontrado
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> getClientById(@PathVariable Long id) {
        log.info("Recibida solicitud para obtener cliente con ID: {}", id);

        ClientResponseDTO client = clientService.readById(id);

        log.info("Cliente encontrado: {} {}", client.getClient_name(), client.getClient_last_name());
        return ResponseEntity.ok(client);
    }

    /**
     * Actualizar un cliente existente
     * @param id ID del cliente a actualizar
     * @param clientRequestDTO Nuevos datos del cliente
     * @return Cliente actualizado
     */
    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> updateClient(
            @PathVariable Integer id,
            @Valid @RequestBody ClientRequestDTO clientRequestDTO) {

        log.info("Recibida solicitud para actualizar cliente con ID: {}", id);

        ClientResponseDTO updatedClient = clientService.update(id, clientRequestDTO);

        log.info("Cliente actualizado exitosamente con ID: {}", updatedClient.getClient_id());
        return ResponseEntity.ok(updatedClient);
    }

    /**
     * Eliminar un cliente (eliminación lógica)
     * @param id ID del cliente a eliminar
     * @return Respuesta sin contenido (204)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Integer id) {
        log.info("Recibida solicitud para eliminar cliente con ID: {}", id);

        clientService.remove(id);

        log.info("Cliente eliminado exitosamente con ID: {}", id);
        return ResponseEntity.noContent().build();
    }
}

