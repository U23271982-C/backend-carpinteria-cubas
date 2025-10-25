package com.content.authentication_service.controller;

import com.content.authentication_service.dto.request.UserEmployeeRequestDTO;
import com.content.authentication_service.dto.response.UserEmployeeResponseDTO;
import com.content.authentication_service.service.UserEmployeeServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user-employee")
@RequiredArgsConstructor
@Slf4j
public class UserEmployeeController {

    private final UserEmployeeServiceImpl userEmployeeServiceImpl;

    @PostMapping
    public ResponseEntity<UserEmployeeResponseDTO> creat(@Valid @RequestBody UserEmployeeRequestDTO dto) {
        UserEmployeeResponseDTO userEmployee = userEmployeeServiceImpl.create(dto);
        return ResponseEntity.ok(userEmployee);
    }

    @GetMapping
    public ResponseEntity<List<UserEmployeeResponseDTO>> findAll() {
        List<UserEmployeeResponseDTO> response = userEmployeeServiceImpl.allList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<UserEmployeeResponseDTO> findByUuid(@PathVariable UUID uuid) {
        log.info("Fetching user employee with ID: {}", uuid);
        UserEmployeeResponseDTO userEmployee = userEmployeeServiceImpl.readById(uuid);

        log.info("User employee encontrado: {} ", userEmployee.getUser_name());
        return ResponseEntity.ok(userEmployee);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<UserEmployeeResponseDTO> update(
            @PathVariable UUID uuid,
            @Valid @RequestBody UserEmployeeRequestDTO dto) {
        UserEmployeeResponseDTO updatedUserEmployee = userEmployeeServiceImpl.update(uuid, dto);
        return ResponseEntity.ok(updatedUserEmployee);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable UUID uuid) {
        userEmployeeServiceImpl.remove(uuid);
        return ResponseEntity.noContent().build();
    }

}
