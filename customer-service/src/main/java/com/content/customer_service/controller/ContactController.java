package com.content.customer_service.controller;

import com.content.customer_service.dto.request.ContactRequestDTO;
import com.content.customer_service.dto.response.ContactResponseDTO;
import com.content.customer_service.service.ContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestión de Contactos
 * Implementa operaciones CRUD siguiendo principios RESTful
 */
@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
@Slf4j
public class ContactController {

    private final ContactService contactService;

    /**
     * Crear un nuevo contacto
     * @param contactRequestDTO Datos del contacto a crear
     * @return Contacto creado con código 201
     */
    @PostMapping
    public ResponseEntity<ContactResponseDTO> createContact(@Valid @RequestBody ContactRequestDTO contactRequestDTO) {
        log.info("Recibida solicitud para crear contacto para cliente UUID: {}",
                contactRequestDTO.getClientUuid());

        ContactResponseDTO createdContact = contactService.create(contactRequestDTO);

        log.info("Contacto creado exitosamente con UUID: {}", createdContact.getUuid());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdContact);
    }

    /**
     * Obtener todos los contactos
     * @return Lista de todos los contactos
     */
    @GetMapping
    public ResponseEntity<List<ContactResponseDTO>> getAllContacts() {
        log.info("Recibida solicitud para obtener todos los contactos");

        List<ContactResponseDTO> contacts = contactService.getAll();

        log.info("Se encontraron {} contactos", contacts.size());
        return ResponseEntity.ok(contacts);
    }

    /**
     * Obtener un contacto por su UUID
     * @param id UUID del contacto
     * @return Contacto encontrado
     */
    @GetMapping("/{id}")
    public ResponseEntity<ContactResponseDTO> getContactById(@PathVariable String id) {
        log.info("Recibida solicitud para obtener contacto con UUID: {}", id);

        ContactResponseDTO contact = contactService.getByUuid(id);

        log.info("Contacto encontrado con email: {}", contact.getEmail());
        return ResponseEntity.ok(contact);
    }

    /**
     * Actualizar un contacto existente
     * @param id UUID del contacto a actualizar
     * @param contactRequestDTO Nuevos datos del contacto
     * @return Contacto actualizado
     */
    @PutMapping("/{id}")
    public ResponseEntity<ContactResponseDTO> updateContact(
            @PathVariable String id,
            @Valid @RequestBody ContactRequestDTO contactRequestDTO) {

        log.info("Recibida solicitud para actualizar contacto con UUID: {}", id);

        ContactResponseDTO updatedContact = contactService.update(id, contactRequestDTO);

        log.info("Contacto actualizado exitosamente con UUID: {}", updatedContact.getUuid());
        return ResponseEntity.ok(updatedContact);
    }

    /**
     * Eliminar un contacto (eliminación lógica)
     * @param id UUID del contacto a eliminar
     * @return Respuesta sin contenido (204)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable String id) {
        log.info("Recibida solicitud para eliminar contacto con UUID: {}", id);

        contactService.delete(id);

        log.info("Contacto eliminado exitosamente con UUID: {}", id);
        return ResponseEntity.noContent().build();
    }
}
