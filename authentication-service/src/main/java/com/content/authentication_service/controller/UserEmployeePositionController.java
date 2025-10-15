package com.content.authentication_service.controller;

import com.content.authentication_service.dto.request.UserEmployeePositionRequestDTO;
import com.content.authentication_service.dto.response.UserEmployeePositionResponseDTO;
import com.content.authentication_service.model.UserEmployeePosition;
import com.content.authentication_service.service.UserEmployeePositionServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user-employee-position")
@RequiredArgsConstructor
@Slf4j
public class UserEmployeePositionController {

    private final UserEmployeePositionServiceImpl userEmployeePositionServiceImpl;

    @PostMapping
    public ResponseEntity<UserEmployeePositionResponseDTO> create(@Valid @RequestBody UserEmployeePositionRequestDTO dto){
        UserEmployeePositionResponseDTO userEmployeePosition = userEmployeePositionServiceImpl.create(dto);
        return ResponseEntity.ok(userEmployeePosition);
    }

    @GetMapping
    public ResponseEntity<List<UserEmployeePositionResponseDTO>> findAll(){
        List<UserEmployeePositionResponseDTO> response = userEmployeePositionServiceImpl.allList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<UserEmployeePositionResponseDTO> findByUuid(@PathVariable UUID uuid){
            log.info("Fetching user employee position with ID: {}", uuid);
            UserEmployeePositionResponseDTO userEmployeePosition = userEmployeePositionServiceImpl.readById(uuid);

            log.info("User employee position encontrado: {} ", userEmployeePosition.getPositionName());
            return ResponseEntity.ok(userEmployeePosition);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<UserEmployeePositionResponseDTO> update(
            @PathVariable UUID uuid,
            @Valid @RequestBody UserEmployeePositionRequestDTO dto){
        UserEmployeePositionResponseDTO updatedUserEmployeePosition = userEmployeePositionServiceImpl.update(uuid, dto);
        return ResponseEntity.ok(updatedUserEmployeePosition);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable UUID uuid) {
        userEmployeePositionServiceImpl.remove(uuid);
        return ResponseEntity.noContent().build();
    }


}
