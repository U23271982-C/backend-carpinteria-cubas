package com.content.authentication_service.controller;

import com.content.authentication_service.dto.request.ModuleRequestDTO;
import com.content.authentication_service.dto.response.ModuleResponseDTO;
import com.content.authentication_service.service.ModuleServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/module")
@RequiredArgsConstructor
@Slf4j
public class ModuleController {

    private final ModuleServiceImpl moduleServiceImpl;

    @PostMapping
    public ResponseEntity<ModuleResponseDTO> create(@Valid @RequestBody ModuleRequestDTO dto){
        ModuleResponseDTO moduleResponseDTO = moduleServiceImpl.create(dto);
        return ResponseEntity.ok(moduleResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ModuleResponseDTO>> findAll(){
        List<ModuleResponseDTO> response = moduleServiceImpl.allList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ModuleResponseDTO> findByUuid(@PathVariable UUID uuid){
        log.info("Fetching user employee position with ID: {}", uuid);
        ModuleResponseDTO module = moduleServiceImpl.readById(uuid);

        log.info("User employee position encontrado: {} ", module.getModule_name());
        return ResponseEntity.ok(module);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<ModuleResponseDTO> update(
            @PathVariable UUID uuid,
            @Valid @RequestBody ModuleRequestDTO dto){
        ModuleResponseDTO module = moduleServiceImpl.update(uuid, dto);
        return ResponseEntity.ok(module);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable UUID uuid) {
        moduleServiceImpl.remove(uuid);
        return ResponseEntity.noContent().build();
    }
}
