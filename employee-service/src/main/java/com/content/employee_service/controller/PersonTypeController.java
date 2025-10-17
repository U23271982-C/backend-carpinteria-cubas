package com.content.employee_service.controller;

import com.content.employee_service.dto.request.PersonTypeRequestDTO;
import com.content.employee_service.dto.response.PersonTypeResponseDTO;
import com.content.employee_service.model.PersonType;
import com.content.employee_service.service.PersonTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/person-type")
@RequiredArgsConstructor
@Slf4j
public class PersonTypeController {
    private final PersonTypeService personTypeService;

    @PostMapping
    public ResponseEntity<PersonTypeResponseDTO> createPersonType
            (@Valid @RequestBody PersonTypeRequestDTO personTypeRequestDTO) {
        log.info("Recibida solicitud para crear tipo de persona");

        PersonTypeResponseDTO dtoResponse = personTypeService.create(personTypeRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(dtoResponse);
    }

    @GetMapping
    public ResponseEntity<Iterable<PersonTypeResponseDTO>> getAllPersonTypes() {
        log.info("Recibida solicitud para obtener todos los tipos de persona");

        Iterable<PersonTypeResponseDTO> personTypes = personTypeService.allList();

        return ResponseEntity.ok(personTypes);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<PersonTypeResponseDTO> readByUUID(@PathVariable UUID uuid) {
        log.info("Recibida solicitud para obtener tipo de persona por su UUID");

        return ResponseEntity.ok(personTypeService.readByUUID(uuid));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<PersonTypeResponseDTO> updateByUUID
            (@PathVariable UUID uuid, @Valid @RequestBody PersonTypeRequestDTO personTypeRequestDTO) {
        log.info("Recibida solicitud para actualizar tipo de persona por su UUID");

        return ResponseEntity.ok(personTypeService.updateByUUID(uuid, personTypeRequestDTO));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> removeByUUID(@PathVariable UUID uuid) {
        log.info("Recibida solicitud para eliminar tipo de persona por su UUID");

        personTypeService.remove(uuid);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("PersonTypeController.test()");
    }
}
