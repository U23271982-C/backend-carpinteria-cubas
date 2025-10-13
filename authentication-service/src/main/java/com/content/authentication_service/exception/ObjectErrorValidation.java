package com.content.authentication_service.exception;

/**
 * Clase que sirve como DTO, para transportar los errores de validaciones
 * @param field
 * @param message
 */
public record ObjectErrorValidation(String field, String message) {
}
