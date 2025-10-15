package com.content.employee_service.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Maneja validaciones manuales lanzadas desde UtilityValidator o lógica interna.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handle(Exception ex) {
        MappingExecption mapping = MappingExecption.fromException(ex);

        // Si error de validacion manual
        if (ex instanceof EValidation ev) {
            return ResponseEntity.status(mapping.getHttpStatus())
                    .body(new ErrorResponseDTO(mapping.getTitle(), ex.getMessage(), ev.getListErrors()));
        }

        // Devuelve error genérico por defecto
        return ResponseEntity.status(mapping.getHttpStatus())
                .body(new ErrorResponseDTO(mapping.getTitle(), ex.getMessage()));
    }

    /**
     * Maneja errores de validación generados por @Valid o @Validated en los controladores.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidation(MethodArgumentNotValidException ex) {
        List<ObjectErrorValidation> listE = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> new ObjectErrorValidation
                                (error.getField(), error.getDefaultMessage()))
                .toList();

        MappingExecption mapping = MappingExecption.fromException(ex);

        return ResponseEntity.status(mapping.getHttpStatus())
                .body(new ErrorResponseDTO(
                        mapping.getTitle(),
                        "Error de validación en uno o más campos.",
                        listE
                ));
    }
}
