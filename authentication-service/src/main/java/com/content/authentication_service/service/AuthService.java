package com.content.authentication_service.service;

import com.content.authentication_service.dto.request.ChangePasswordRequestDTO;
import com.content.authentication_service.dto.request.LoginUserRequestDTO;
import com.content.authentication_service.jwt.JwtUtil;
import com.content.authentication_service.model.UserEmployee;
import com.content.authentication_service.repository.UserEmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AuthService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtUtil jwtUtil;
    private final UserEmployeeRepository userEmployeeRepository;
    private final PasswordEncoder passwordEncoder;

    public String authenticate(LoginUserRequestDTO dto) {
        UserEmployee user = userEmployeeRepository.findByUsername(dto.getUsername()).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Credenciales incorrectas");
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUuid().toString(),dto.getPassword());
        Authentication authResult = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authResult);
        return jwtUtil.generateToken(authResult);
    }

    public String changePassword(UUID employeeUuid, ChangePasswordRequestDTO dto) {
        UserEmployee userEmployee = userEmployeeRepository.findByUuid(employeeUuid).orElseThrow(()-> new RuntimeException("Usuario empleado no encontrado"));
        if (!passwordEncoder.matches(dto.getPassword(), userEmployee.getPassword())) {
            throw new RuntimeException("Contraseña actual incorrecta");
        }
        if (!dto.getNewPassword().equals(dto.getConfirmPassword())) {
            throw new RuntimeException("Las contraseñas no coinciden");
        }
        userEmployee.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        userEmployeeRepository.save(userEmployee);
        return "Contraseña actualizada exitosamente";
    }
}
