package com.content.sale_service.execption;


import lombok.Getter;

import java.util.List;

/**
 * Exepcion para validaciones de los DTOs
 */
@Getter
public class ExecptionValidation extends RuntimeException {
    private final List<ObjectErrorValidation> listErrors;

    public ExecptionValidation(List<ObjectErrorValidation> listErrors) {
        super("Error de validación");
        this.listErrors = listErrors;
    }
}
