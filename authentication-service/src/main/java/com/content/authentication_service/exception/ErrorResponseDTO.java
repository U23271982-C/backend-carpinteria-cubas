package com.content.authentication_service.exception;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Clase que transporta los errores, expulsarlo al front
 * @param title titulo del error
 * @param message mesage del error
 * @param listErrors lista de errores
 * @param timestamp fecha y hora del error
 */
public record ErrorResponseDTO(
        String title,
        String message,
        List<ObjectErrorValidation> listErrors,
        LocalDateTime timestamp
) {
    public ErrorResponseDTO(String title, String message, List<ObjectErrorValidation> listErrors) {
        this(title, message, listErrors, LocalDateTime.now());
    }

    public ErrorResponseDTO(String title, String message) {
        this(title, message, null, LocalDateTime.now());
    }
}
