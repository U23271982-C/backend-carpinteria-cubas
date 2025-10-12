package com.content.authentication_service.controller;

import com.content.authentication_service.dto.request.UserClientRequestDTO;
import com.content.authentication_service.dto.response.UserClientResponseDTO;
import com.content.authentication_service.service.UserClientServiceImpl; // Importa la implementación
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user-clients") // URL base para todos los endpoints de este controlador
@RequiredArgsConstructor
public class UserClientController {

    // Inyectamos la implementación directamente para acceder a los métodos con UID
    private final UserClientServiceImpl userClientService;

    // --- Endpoint para CREAR un usuario ---
    @PostMapping
    public ResponseEntity<UserClientResponseDTO> createUser(@RequestBody UserClientRequestDTO requestDTO) {
        UserClientResponseDTO createdUser = userClientService.create(requestDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // --- Endpoint para OBTENER un usuario por su UID de Firebase ---
    @GetMapping("/{uid}")
    public ResponseEntity<UserClientResponseDTO> getUserByUid(@PathVariable String uid) {
        UserClientResponseDTO user = userClientService.findByUid(uid);
        return ResponseEntity.ok(user);
    }

    // --- Endpoint para OBTENER TODOS los usuarios ---
    @GetMapping
    public ResponseEntity<List<UserClientResponseDTO>> getAllUsers() {
        List<UserClientResponseDTO> users = userClientService.allList();
        return ResponseEntity.ok(users);
    }

    // --- Endpoint para ACTUALIZAR un usuario por su UID de Firebase ---
    @PutMapping("/{uid}")
    public ResponseEntity<UserClientResponseDTO> updateUserByUid(@PathVariable String uid, @RequestBody UserClientRequestDTO requestDTO) {
        UserClientResponseDTO updatedUser = userClientService.updateByUid(uid, requestDTO);
        return ResponseEntity.ok(updatedUser);
    }

    // --- Endpoint para ELIMINAR (soft-delete) un usuario por su UID de Firebase ---
    @DeleteMapping("/{uid}")
    public ResponseEntity<Void> deleteUserByUid(@PathVariable String uid) {
        userClientService.removeByUid(uid);
        return ResponseEntity.noContent().build(); // Devuelve un estado 204 No Content
    }
}