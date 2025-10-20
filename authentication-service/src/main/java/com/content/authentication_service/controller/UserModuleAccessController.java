package com.content.authentication_service.controller;

import com.content.authentication_service.dto.request.UserModuleAccessRequestDTO;
import com.content.authentication_service.dto.response.UserModuleAccessResponseDTO;
import com.content.authentication_service.service.UserModuleAccessServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user-module-access")
@RequiredArgsConstructor
@Slf4j
public class UserModuleAccessController {

    private final UserModuleAccessServiceImpl userModuleAccessService;

    @PostMapping
    public ResponseEntity<UserModuleAccessResponseDTO> create(@Valid @RequestBody UserModuleAccessRequestDTO dto){
        UserModuleAccessResponseDTO userModuleAccess = userModuleAccessService.create(dto);
        return ResponseEntity.ok(userModuleAccess);
    }

    @GetMapping
    public ResponseEntity<List<UserModuleAccessResponseDTO>> findAll(){
        List<UserModuleAccessResponseDTO> response = userModuleAccessService.allList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-u-e/{uuidemployee}")
    public ResponseEntity<List<UserModuleAccessResponseDTO>> findAllModulesByUserEmployee(@PathVariable UUID uuidemployee){
        List<UserModuleAccessResponseDTO> response = userModuleAccessService.getByUserEmployeeUuid(uuidemployee);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<UserModuleAccessResponseDTO> findByUuid(@PathVariable UUID uuid){
            log.info("Fetching user employee position with ID: {}", uuid);
            UserModuleAccessResponseDTO userModuleAccess = userModuleAccessService.readById(uuid);

            log.info("User employee position encontrado: {} ", userModuleAccess.getUuid());
            return ResponseEntity.ok(userModuleAccess);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<UserModuleAccessResponseDTO> update(
            @PathVariable UUID uuid,
            @Valid @RequestBody UserModuleAccessRequestDTO dto){
        UserModuleAccessResponseDTO updatedUserModuleAccess = userModuleAccessService.update(uuid, dto);
        return ResponseEntity.ok(updatedUserModuleAccess);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable UUID uuid) {
        userModuleAccessService.remove(uuid);
        return ResponseEntity.noContent().build();
    }

}
