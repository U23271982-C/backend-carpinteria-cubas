package com.content.customer_service.exception;

import lombok.Getter;
import java.util.List;

/**
 * Excepción personalizada para errores de validación
 */
@Getter
public class EValidation extends RuntimeException {
    private final List<ObjectErrorValidation> listErrors;

    public EValidation(String message, List<ObjectErrorValidation> listErrors) {
        super(message);
        this.listErrors = listErrors;
    }
}

