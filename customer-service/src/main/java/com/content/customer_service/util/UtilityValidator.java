package com.content.customer_service.util;

import com.content.customer_service.exception.EValidation;
import com.content.customer_service.exception.ObjectErrorValidation;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Utilidad para validar DTOs usando las anotaciones de validación
 */
@Component
@RequiredArgsConstructor
public class UtilityValidator {

    private final Validator validator;

    /**
     * Valida un objeto y lanza una excepción si hay errores
     * @param object Objeto a validar
     * @param <T> Tipo del objeto
     */
    public <T> void validate(T object) {
        Set<ConstraintViolation<T>> errores = validator.validate(object);
        mapMessageErrores(errores);
    }

    private static <T> void mapMessageErrores(Set<ConstraintViolation<T>> errores) {
        if (!errores.isEmpty()) {
            List<ObjectErrorValidation> listErrores = errores.stream()
                    .map(error ->
                            new ObjectErrorValidation(error.getPropertyPath().toString(), error.getMessage())).toList();

            throw new EValidation(listErrores);
        }
    }
}

