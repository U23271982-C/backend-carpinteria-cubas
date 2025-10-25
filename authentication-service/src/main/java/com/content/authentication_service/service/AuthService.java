package com.content.authentication_service.service;

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

    public String authenticate(String username, String password){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);
        Authentication authResult = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authResult);
        return jwtUtil.generateToken(authResult);
    }

    public String changePassword(UUID employeeUuid, String password, String newPassword, String confirmPassword) {
        UserEmployee userEmployee = userEmployeeRepository.findByUuid(employeeUuid).orElseThrow(()-> new RuntimeException("Usuario empleado no encontrado"));
        if (!passwordEncoder.matches(password, userEmployee.getPassword())) {
            throw new RuntimeException("Contraseña actual incorrecta");
        }
        if (!newPassword.equals(confirmPassword)) {
            throw new RuntimeException("Las contraseñas no coinciden");
        }
        userEmployee.setPassword(passwordEncoder.encode(newPassword));
        userEmployeeRepository.save(userEmployee);
        return "Contraseña actualizada exitosamente";
    }
}
