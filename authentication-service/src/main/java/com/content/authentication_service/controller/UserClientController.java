package com.content.authentication_service.controller;

import com.content.authentication_service.dto.request.UserClientRequestDTO;
import com.content.authentication_service.dto.response.UserClientResponseDTO;
import com.content.authentication_service.service.UserClientServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-clients")
@RequiredArgsConstructor
@Slf4j
public class UserClientController {

    private final UserClientServiceImpl userClientService;

    @PostMapping
    public ResponseEntity<UserClientResponseDTO> create(@Valid @RequestBody UserClientRequestDTO dto) {
        UserClientResponseDTO response = userClientService.create(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<UserClientResponseDTO>> findAll() {
        List<UserClientResponseDTO> users = userClientService.allList();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserClientResponseDTO> findById(@PathVariable Long id) {
        log.info("Fetching user client with ID: {}", id);
        UserClientResponseDTO user = userClientService.readById(id);

        log.info("User encontrado: {} {}", user.getFullName(), user.getFire_base_uid());
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        userClientService.remove(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserClientResponseDTO> update(
            @PathVariable int id,
            @Valid @RequestBody UserClientRequestDTO dto) {
        UserClientResponseDTO updatedUser = userClientService.update(id, dto);
        return ResponseEntity.ok(updatedUser);
    }
}