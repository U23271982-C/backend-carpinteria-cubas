package com.content.employee_service.controller;

import com.content.employee_service.dto.request.ContractRequestDTO;
import com.content.employee_service.dto.response.ContractResponseDTO;
import com.content.employee_service.service.ContractService;
import com.content.employee_service.utility.ValidateGroup;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/contract")
@RequiredArgsConstructor
@Slf4j
public class ContractController {
    private final ContractService service;

    @PostMapping
    public ResponseEntity<ContractResponseDTO> createPersonType
            (@Validated(ValidateGroup.OnCreate.class) @RequestBody ContractRequestDTO contractRequestDTO) {
        log.info("Recibida solicitud para crear contrato");

        ContractResponseDTO dtoResponse = service.create(contractRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(dtoResponse);
    }

    @GetMapping
    public ResponseEntity<Iterable<ContractResponseDTO>> getAllContracts() {
        log.info("Recibida solicitud para obtener todos los contratos");

        Iterable<ContractResponseDTO> contracts = service.allList();

        return ResponseEntity.ok(contracts);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ContractResponseDTO> readByUUID(@PathVariable UUID uuid) {
        log.info("Recibida solicitud para obtener contrato por su UUID");

        return ResponseEntity.ok(service.readByUUID(uuid));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<ContractResponseDTO> updateByUUID
            (@PathVariable UUID uuid, @Valid @RequestBody ContractRequestDTO contractRequestDTO) {
        log.info("Recibida solicitud para actualizar contrato por su UUID");

        return ResponseEntity.ok(service.updateByUUID(uuid, contractRequestDTO));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> removeByUUID(@PathVariable UUID uuid) {
        log.info("Recibida solicitud para eliminar contrato por su UUID");

        service.remove(uuid);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("FUNCIONALITY TEST");
    }
}
