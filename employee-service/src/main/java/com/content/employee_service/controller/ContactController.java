package com.content.employee_service.controller;

import com.content.employee_service.dto.request.ContactRequestDTO;
import com.content.employee_service.dto.response.ContactResponseDTO;
import com.content.employee_service.service.ContactService;
import com.content.employee_service.utility.ValidateGroup;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/contact")
@RequiredArgsConstructor
@Slf4j
public class ContactController {
    private final ContactService service;

    @PostMapping
    public ResponseEntity<ContactResponseDTO> createContact
            (@Validated(ValidateGroup.OnCreate.class) @RequestBody ContactRequestDTO contactRequestDTO) {
        log.info("Recibida solicitud para crear contacto");

        ContactResponseDTO dtoResponse = service.create(contactRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(dtoResponse);
    }

    @GetMapping
    public ResponseEntity<Iterable<ContactResponseDTO>> getAllContacts() {
        log.info("Recibida solicitud para obtener todos los contacto");

        Iterable<ContactResponseDTO> contracts = service.allList();

        return ResponseEntity.ok(contracts);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ContactResponseDTO> readByUUID(@PathVariable UUID uuid) {
        log.info("Recibida solicitud para obtener contacto por su UUID");

        return ResponseEntity.ok(service.readByUUID(uuid));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<ContactResponseDTO> updateByUUID
            (@PathVariable UUID uuid, @Validated(ValidateGroup.OnUpdate.class) @RequestBody ContactRequestDTO contactRequestDTO) {
        log.info("Recibida solicitud para actualizar contacto por su UUID");

        return ResponseEntity.ok(service.updateByUUID(uuid, contactRequestDTO));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> removeByUUID(@PathVariable UUID uuid) {
        log.info("Recibida solicitud para eliminar contacto por su UUID");

        service.remove(uuid);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("FUNCTIONALITY TEST");
    }
}
