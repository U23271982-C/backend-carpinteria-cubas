package com.content.sale_service.execption;

/**
 * Clase que sirve como DTO, para transportar los errores de validaciones
 * @param field
 * @param message
 */
public record ObjectErrorValidation(String field, String message) {
}
