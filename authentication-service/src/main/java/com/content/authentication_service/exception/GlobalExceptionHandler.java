package com.content.authentication_service.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handle(Exception ex) {
        MappingExecption mapping = MappingExecption.fromException(ex);

        if (ex instanceof EValidation ev) {
            return ResponseEntity.status(mapping.getHttpStatus())
                    .body(new ErrorResponseDTO(mapping.getTitle(), ex.getMessage(), ev.getListErrors()));
        }

        return ResponseEntity.status(mapping.getHttpStatus())
                .body(new ErrorResponseDTO(mapping.getTitle(), ex.getMessage()));
    }
}
