package com.content.employee_service.controller;

import com.content.employee_service.dto.request.IdentificationTypeRequestDTO;
import com.content.employee_service.dto.response.IdentificationTypeResponseDTO;
import com.content.employee_service.service.IdentificationTypeService;
import com.content.employee_service.utility.ValidateGroup;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/identification-type")
@RequiredArgsConstructor
@Slf4j
public class IdentificationTypeController {
    private final IdentificationTypeService service;

    @PostMapping
    public ResponseEntity<IdentificationTypeResponseDTO> createIdentificationType
            (@Validated(ValidateGroup.OnCreate.class) @RequestBody IdentificationTypeRequestDTO identificationTypeRequestDTO) {
        log.info("Recibida solicitud para crear contrato");

        IdentificationTypeResponseDTO dtoResponse = service.create(identificationTypeRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(dtoResponse);
    }

    @GetMapping
    public ResponseEntity<Iterable<IdentificationTypeResponseDTO>> getAllContracts() {
        log.info("Recibida solicitud para obtener todos los contratos");

        Iterable<IdentificationTypeResponseDTO> contracts = service.allList();

        return ResponseEntity.ok(contracts);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<IdentificationTypeResponseDTO> readByUUID(@PathVariable UUID uuid) {
        log.info("Recibida solicitud para obtener contrato por su UUID");

        return ResponseEntity.ok(service.readByUUID(uuid));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<IdentificationTypeResponseDTO> updateByUUID
            (@PathVariable UUID uuid, @Validated(ValidateGroup.OnUpdate.class) @RequestBody IdentificationTypeRequestDTO identificationTypeRequestDTO) {
        log.info("Recibida solicitud para actualizar contrato por su UUID");

        return ResponseEntity.ok(service.updateByUUID(uuid, identificationTypeRequestDTO));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> removeByUUID(@PathVariable UUID uuid) {
        log.info("Recibida solicitud para eliminar contrato por su UUID");

        service.remove(uuid);
        return ResponseEntity.noContent().build();
    }
    @RequestMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("IdentificationTypeController.test()");
    }
}
