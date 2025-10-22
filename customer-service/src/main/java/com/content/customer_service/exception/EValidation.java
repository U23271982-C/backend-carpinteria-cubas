package com.content.customer_service.exception;

import lombok.Getter;
import java.util.List;

/**
 * Excepción personalizada para errores de validación
 */
@Getter
public class EValidation extends RuntimeException {
    private final List<ObjectErrorValidation> listErrors;

    public EValidation(List<ObjectErrorValidation> listErrors) {
        super("Error de validación");
        this.listErrors = listErrors;
    }
}

