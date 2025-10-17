package com.content.employee_service.controller;

import com.content.employee_service.dto.request.ContractRequestDTO;
import com.content.employee_service.dto.request.EmployeeRequestDTO;
import com.content.employee_service.dto.response.ContractResponseDTO;
import com.content.employee_service.dto.response.EmployeeResponseDTO;
import com.content.employee_service.service.EmployeeService;
import com.content.employee_service.utility.ValidateGroup;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {
    private final EmployeeService service;

    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> createPersonType
            (@Validated(ValidateGroup.OnCreate.class) @RequestBody EmployeeRequestDTO employeeRequestDTO) {
        log.info("Recibida solicitud para crear empleado");

        EmployeeResponseDTO dtoResponse = service.create(employeeRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(dtoResponse);
    }

    @GetMapping
    public ResponseEntity<Iterable<EmployeeResponseDTO>> getAllContracts() {
        log.info("Recibida solicitud para obtener todos los empleados");

        Iterable<EmployeeResponseDTO> contracts = service.allList();

        return ResponseEntity.ok(contracts);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<EmployeeResponseDTO> readByUUID(@PathVariable UUID uuid) {
        log.info("Recibida solicitud para obtener empleado por su UUID");

        return ResponseEntity.ok(service.readByUUID(uuid));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<EmployeeResponseDTO> updateByUUID
            (@PathVariable UUID uuid, @Validated(ValidateGroup.OnUpdate.class) @RequestBody EmployeeRequestDTO employeeRequestDTO) {
        log.info("Recibida solicitud para actualizar empleado por su UUID");

        return ResponseEntity.ok(service.updateByUUID(uuid, employeeRequestDTO));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> removeByUUID(@PathVariable UUID uuid) {
        log.info("Recibida solicitud para eliminar empleado por su UUID");

        service.remove(uuid);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("FUNCTIONALITY TEST");
    }
}
