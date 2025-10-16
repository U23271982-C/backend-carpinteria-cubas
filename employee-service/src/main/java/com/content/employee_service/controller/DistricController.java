package com.content.employee_service.controller;

import com.content.employee_service.dto.request.DistrictRequestDTO;
import com.content.employee_service.dto.response.DistrictResponseDTO;
import com.content.employee_service.service.DistricService;
import com.content.employee_service.utility.ValidateGroup;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/district")
@RequiredArgsConstructor
@Slf4j
public class DistricController {
    private final DistricService service;

    @PostMapping
    public ResponseEntity<DistrictResponseDTO> createDistrict
            (@Validated(ValidateGroup.OnCreate.class) @RequestBody DistrictRequestDTO contractRequestDTO) {
        log.info("Recibida solicitud para crear distrito");

        DistrictResponseDTO dtoResponse = service.create(contractRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(dtoResponse);
    }

    @GetMapping
    public ResponseEntity<Iterable<DistrictResponseDTO>> getAllContracts() {
        log.info("Recibida solicitud para obtener todos los distritos");

        Iterable<DistrictResponseDTO> contracts = service.allList();

        return ResponseEntity.ok(contracts);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<DistrictResponseDTO> readByUUID(@PathVariable UUID uuid) {
        log.info("Recibida solicitud para obtener distrito por su UUID");

        return ResponseEntity.ok(service.readByUUID(uuid));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<DistrictResponseDTO> updateByUUID
            (@PathVariable UUID uuid, @Validated(ValidateGroup.OnUpdate.class) @RequestBody DistrictRequestDTO contractRequestDTO) {
        log.info("Recibida solicitud para actualizar distrito por su UUID");

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
        return ResponseEntity.ok("FUNCTIONALITY TEST");
    }
}
