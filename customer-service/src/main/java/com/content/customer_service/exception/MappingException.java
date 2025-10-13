package com.content.customer_service.exception;

import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Mapeo de excepciones a códigos HTTP y títulos
 */
@Getter
public enum MappingException {
    VALIDATION_ERROR(EValidation.class, HttpStatus.BAD_REQUEST, "Error de Validación"),
    ENTITY_NOT_FOUND(EntityNotFoundException.class, HttpStatus.NOT_FOUND, "Entidad No Encontrada"),
    ILLEGAL_ARGUMENT(IllegalArgumentException.class, HttpStatus.BAD_REQUEST, "Argumento Inválido"),
    RUNTIME_EXCEPTION(RuntimeException.class, HttpStatus.INTERNAL_SERVER_ERROR, "Error Interno del Servidor"),
    GENERIC_EXCEPTION(Exception.class, HttpStatus.INTERNAL_SERVER_ERROR, "Error Interno del Servidor");

    private final Class<? extends Exception> exceptionClass;
    private final HttpStatus httpStatus;
    private final String title;

    MappingException(Class<? extends Exception> exceptionClass, HttpStatus httpStatus, String title) {
        this.exceptionClass = exceptionClass;
        this.httpStatus = httpStatus;
        this.title = title;
    }

    /**
     * Obtiene el mapeo correspondiente a una excepción
     */
    public static MappingException fromException(Exception ex) {
        for (MappingException mapping : values()) {
            if (mapping.getExceptionClass().isInstance(ex)) {
                return mapping;
            }
        }
        return GENERIC_EXCEPTION;
    }
}

