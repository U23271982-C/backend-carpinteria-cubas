package com.content.employee_service.controller;

import com.content.employee_service.dto.request.ContractTypeRequestDTO;
import com.content.employee_service.dto.response.ContractTypeResponseDTO;
import com.content.employee_service.service.ContractTypeService;
import com.content.employee_service.utility.ValidateGroup;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/contract-type")
@RequiredArgsConstructor
@Slf4j
public class ContractTypeController {
    private final ContractTypeService service;
    @PostMapping
    public ResponseEntity<ContractTypeResponseDTO> createContractType
            (@Validated(ValidateGroup.OnCreate.class) @RequestBody ContractTypeRequestDTO contractTypeRequestDTO) {
        log.info("Recibida solicitud para crear tipo de contrato");

        ContractTypeResponseDTO dtoResponse = service.create(contractTypeRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(dtoResponse);
    }

    @GetMapping
    public ResponseEntity<Iterable<ContractTypeResponseDTO>> getAllContractTypes() {
        log.info("Recibida solicitud para obtener todos los tipos de contrato");

        Iterable<ContractTypeResponseDTO> contractTypes = service.allList();

        return ResponseEntity.ok(contractTypes);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ContractTypeResponseDTO> readByUUID(@PathVariable UUID uuid) {
        log.info("Recibida solicitud para obtener tipo de contrato por su UUID");

        return ResponseEntity.ok(service.readByUUID(uuid));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<ContractTypeResponseDTO> updateByUUID
            (@PathVariable UUID uuid, @Validated(ValidateGroup.OnUpdate.class) @RequestBody ContractTypeRequestDTO contractTypeRequestDTO) {
        log.info("Recibida solicitud para actualizar tipo de contrato por su UUID");

        return ResponseEntity.ok(service.updateByUUID(uuid, contractTypeRequestDTO));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> removeByUUID(@PathVariable UUID uuid) {
        log.info("Recibida solicitud para eliminar tipo de contrato por su UUID");

        service.remove(uuid);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("ContractTypeController.test()");
    }
}
