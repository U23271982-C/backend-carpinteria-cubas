package com.content.authentication_service.controller;

import com.content.authentication_service.dto.request.UserAccessActionRequestDTO;
import com.content.authentication_service.dto.response.UserAccessActionResponseDTO;
import com.content.authentication_service.service.UserAccessActionServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user-access-action")
@RequiredArgsConstructor
@Slf4j
public class UserAccessActionController {

    private final UserAccessActionServiceImpl userAccessActionService;

    @PostMapping
    public ResponseEntity<UserAccessActionResponseDTO> create(@Valid @RequestBody UserAccessActionRequestDTO dto){
        UserAccessActionResponseDTO userModuleAccess = userAccessActionService.create(dto);
        return ResponseEntity.ok(userModuleAccess);
    }

    @GetMapping
    public ResponseEntity<List<UserAccessActionResponseDTO>> findAll(){
        List<UserAccessActionResponseDTO> response = userAccessActionService.allList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-u-m/{uuiduma}")
    public ResponseEntity<List<UserAccessActionResponseDTO>> findAllModulesByUserEmployee(@PathVariable UUID uuiduma){
        List<UserAccessActionResponseDTO> response = userAccessActionService.getByUserModuleAccess(uuiduma);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<UserAccessActionResponseDTO> findByUuid(@PathVariable UUID uuid){
            log.info("Fetching user employee position with ID: {}", uuid);
            UserAccessActionResponseDTO userModuleAccess = userAccessActionService.readById(uuid);

            log.info("User employee position encontrado: {} ", userModuleAccess.getUuid());
            return ResponseEntity.ok(userModuleAccess);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<UserAccessActionResponseDTO> update(
            @PathVariable UUID uuid,
            @Valid @RequestBody UserAccessActionRequestDTO dto){
        UserAccessActionResponseDTO updatedUserModuleAccess = userAccessActionService.update(uuid, dto);
        return ResponseEntity.ok(updatedUserModuleAccess);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable UUID uuid) {
        userAccessActionService.remove(uuid);
        return ResponseEntity.noContent().build();
    }


}
