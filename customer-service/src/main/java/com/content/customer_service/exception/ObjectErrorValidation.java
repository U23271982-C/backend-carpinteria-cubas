package com.content.customer_service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Objeto que representa un error de validación
 */
@Getter
@Setter
@AllArgsConstructor
public class ObjectErrorValidation {
    private String field;
    private String message;
}

