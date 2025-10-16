package com.content.employee_service.controller;

import com.content.employee_service.dto.request.PostRequestDTO;
import com.content.employee_service.dto.response.PostResponseDTO;
import com.content.employee_service.service.PostService;
import com.content.employee_service.utility.ValidateGroup;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
@Slf4j
public class PostController {
    private final PostService service;

    @PostMapping
    public ResponseEntity<PostResponseDTO> createPost
            (@Validated(ValidateGroup.OnCreate.class) @RequestBody PostRequestDTO postRequestDTO) {
        log.info("Recibida solicitud para crear el cargo");

        PostResponseDTO dtoResponse = service.create(postRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(dtoResponse);
    }

    @GetMapping
    public ResponseEntity<Iterable<PostResponseDTO>> getAllContracts() {
        log.info("Recibida solicitud para obtener todos los cargos");

        Iterable<PostResponseDTO> contracts = service.allList();

        return ResponseEntity.ok(contracts);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<PostResponseDTO> readByUUID(@PathVariable UUID uuid) {
        log.info("Recibida solicitud para obtener cargo por su UUID");

        return ResponseEntity.ok(service.readByUUID(uuid));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<PostResponseDTO> updateByUUID
            (@PathVariable UUID uuid, @Validated(ValidateGroup.OnUpdate.class) @RequestBody PostRequestDTO contractRequestDTO) {
        log.info("Recibida solicitud para actualizar el cargo por su UUID");

        return ResponseEntity.ok(service.updateByUUID(uuid, contractRequestDTO));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> removeByUUID(@PathVariable UUID uuid) {
        log.info("Recibida solicitud para eliminar el cargo por su UUID");

        service.remove(uuid);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("PostController.test()");
    }
}
