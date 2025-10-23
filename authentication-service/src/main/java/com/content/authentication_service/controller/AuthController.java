package com.content.authentication_service.controller;

import com.content.authentication_service.dto.request.LoginUserRequestDTO;
import com.content.authentication_service.service.AuthService;
import com.content.authentication_service.service.SessionServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService authService;
    private final SessionServiceImpl sessionServiceImpl;

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginUserRequestDTO loginUserRequestDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body("Revise sus credenciales");
        }
        try{
            String jwt = authService.authenticate(loginUserRequestDTO.getUsername(), loginUserRequestDTO.getPassword());
            sessionServiceImpl.create(loginUserRequestDTO);
            return ResponseEntity.ok(jwt);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/check-auth")
    public ResponseEntity<String> checkAuth(){
        return ResponseEntity.ok().body("Autenticado");
    }
}
