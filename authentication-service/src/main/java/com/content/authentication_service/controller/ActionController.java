package com.content.authentication_service.controller;

import com.content.authentication_service.dto.response.ActionResponseDTO;
import com.content.authentication_service.service.ActionServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/action")
@RequiredArgsConstructor
@Slf4j
public class ActionController {

    private final ActionServiceImpl actionServiceImpl;

    public ResponseEntity<List<ActionResponseDTO>> findAll(){
        List<ActionResponseDTO> response = actionServiceImpl.allList();
        return ResponseEntity.ok(response);
    }
}
