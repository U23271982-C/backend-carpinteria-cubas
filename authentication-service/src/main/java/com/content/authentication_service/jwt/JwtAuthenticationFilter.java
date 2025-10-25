package com.content.authentication_service.jwt;

import com.content.authentication_service.model.UserEmployee;
import com.content.authentication_service.service.UserEmployeeServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@NoArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserEmployeeServiceImpl userEmployeeServiceImpl;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");

        String userName = null; // (Este es el UUID)
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            jwt = authorizationHeader.substring(7);
            userName = jwtUtil.extractUserName(jwt);
        }

        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null){

            try {
                UserEmployee userEmployee = userEmployeeServiceImpl.findUserByUuid(userName);
                UserDetails userDetails = userEmployeeServiceImpl.loadUserByUsername(userName);

                // 3. Valida el token PASANDO EL TIMESTAMP
                if (jwtUtil.validateToken(jwt, userDetails, userEmployee.getLastPasswordChangeTimestamp())) {
                    List<GrantedAuthority> authorities = jwtUtil.extractAuthorities(jwt);

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }

            } catch (UsernameNotFoundException e) {
                // El usuario no existe o está inactivo, no hacer nada,
                // la petición será rechazada más adelante.
                logger.warn("Intento de autenticación fallido para JWT: " + e.getMessage());
            }
        }
        filterChain.doFilter(request, response);
    }
}