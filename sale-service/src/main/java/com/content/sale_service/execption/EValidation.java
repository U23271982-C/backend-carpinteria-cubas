package com.content.sale_service.execption;


import lombok.Getter;

import java.util.List;

/**
 * Exepcion para validaciones de los DTOs
 */
@Getter
public class EValidation extends RuntimeException {
    private final List<ObjectErrorValidation> listErrors;

    public EValidation(List<ObjectErrorValidation> listErrors) {
        super("Error de validación");
        this.listErrors = listErrors;
    }
}
