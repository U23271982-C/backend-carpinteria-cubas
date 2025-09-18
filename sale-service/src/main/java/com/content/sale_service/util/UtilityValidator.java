package com.content.sale_service.util;

import com.content.sale_service.execption.ExecptionValidation;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

public class UtilityValidator {
    private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private static final Validator validator = factory.getValidator();

    /**
     * Se valida un dto, con diferentes marcas de grupos de validación
     * Los errores se guradan y se lanza una excepción, forma de orden de llegada
     * @param objeto
     * @param grupos
     * @param <T>
     */
    public static <T> void validate(T objeto, Class<?>... grupos) {
        Set<ConstraintViolation<T>> errores = validator.validate(objeto, grupos);
        if (!errores.isEmpty()) {
            StringBuilder sb = new StringBuilder("Errores de validación:\n");
            for (ConstraintViolation<T> error : errores) {
                sb.append("- ").append(error.getMessage()).append("\n");
            }
            throw new ExecptionValidation(sb.toString());
        }
    }
}
