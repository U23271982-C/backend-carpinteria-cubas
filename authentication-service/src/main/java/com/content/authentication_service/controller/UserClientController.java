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
@RequestMapping("/user-client")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
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

    @GetMapping("/{uid}")
    public ResponseEntity<UserClientResponseDTO> findByFireBaseUID(@PathVariable String uid) {
        log.info("Fetching user client with ID: {}", uid);
        UserClientResponseDTO user = userClientService.readByFirebaseUid(uid);

        log.info("User encontrado: {} {}", user.getFullName(), user.getEmail());
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{uid}")
    public ResponseEntity<UserClientResponseDTO> update(
            @PathVariable String uid,
            @Valid @RequestBody UserClientRequestDTO dto) {
        UserClientResponseDTO updatedUser = userClientService.updateByFirebaseUid(uid, dto);
        return ResponseEntity.ok(updatedUser);
    }
}