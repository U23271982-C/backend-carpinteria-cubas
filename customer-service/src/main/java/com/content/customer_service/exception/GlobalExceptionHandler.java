package com.content.customer_service.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Manejador global de excepciones para todos los controladores
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja todas las excepciones y las convierte en respuestas HTTP apropiadas
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handle(Exception ex) {
        MappingException mapping = MappingException.fromException(ex);

        // Si es una excepción de validación, incluir los errores de validación
        if (ex instanceof EValidation ev) {
            return ResponseEntity.status(mapping.getHttpStatus())
                    .body(new ErrorResponseDTO(mapping.getTitle(), ex.getMessage(), ev.getListErrors()));
        }

        // Para otras excepciones, solo incluir título y mensaje
        return ResponseEntity.status(mapping.getHttpStatus())
                .body(new ErrorResponseDTO(mapping.getTitle(), ex.getMessage()));
    }
}

