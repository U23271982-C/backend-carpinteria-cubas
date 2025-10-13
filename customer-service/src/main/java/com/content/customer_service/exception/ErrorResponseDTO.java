package com.content.customer_service.exception;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

/**
 * DTO para respuestas de error
 */
@Getter
@Setter
public class ErrorResponseDTO {
    private String title;
    private String message;
    private List<ObjectErrorValidation> errors;

    public ErrorResponseDTO(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public ErrorResponseDTO(String title, String message, List<ObjectErrorValidation> errors) {
        this.title = title;
        this.message = message;
        this.errors = errors;
    }
}

