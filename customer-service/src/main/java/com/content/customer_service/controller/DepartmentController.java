package com.content.customer_service.controller;

import com.content.customer_service.dto.request.DepartmentRequestDTO;
import com.content.customer_service.dto.response.DepartmentResponseDTO;
import com.content.customer_service.service.DepartmentService;
import com.content.customer_service.util.ValidatorGroups;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
@Slf4j
public class DepartmentController {
    private final DepartmentService service;

    @PostMapping
    public ResponseEntity<DepartmentResponseDTO> createDepartment(@Validated(ValidatorGroups.Create.class) @RequestBody DepartmentRequestDTO departmentRequestDTO) {
        log.info("Solicitud recibida para crear un departamento");
        DepartmentResponseDTO dtoResponse = service.create(departmentRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(dtoResponse);
    }


    @GetMapping
    public ResponseEntity<Iterable<DepartmentResponseDTO>> getAllDepartments() {
        log.info("Solicitud recibida para obtener todos los departamentos");

        Iterable<DepartmentResponseDTO> departments = service.allList();

        return ResponseEntity.ok(departments);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<DepartmentResponseDTO> readByUUID(@PathVariable java.util.UUID uuid) {
        log.info("Solicitud recibida para obtener un departamento por su UUID");
        return ResponseEntity.ok(service.readByUUID(uuid));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<DepartmentResponseDTO> updateByUUID(
            @PathVariable UUID uuid,
            @Validated(ValidatorGroups.Update.class) @RequestBody DepartmentRequestDTO departmentRequestDTO) {
        log.info("Solicitud recibida para actualizar un departamento por su UUID");
        return ResponseEntity.ok(service.updateByUUID(uuid, departmentRequestDTO));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> removeByUUID(@PathVariable UUID uuid) {
        log.info("Solicitud recibida para eliminar un departamento por su UUID");
        service.remove(uuid);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping("/test")
    public ResponseEntity<String> test() {return ResponseEntity.ok("Los departamentos funcionan correctamente");}
}
