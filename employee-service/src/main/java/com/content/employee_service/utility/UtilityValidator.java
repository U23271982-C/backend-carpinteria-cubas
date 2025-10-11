package com.content.employee_service.utility;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import java.util.List;
import java.util.Set;

public class ValidatorUtility {
    //@Autowired
    private final Validator validator;

    public UtilityValidator(Validator validator) {
        this.validator = validator;
    }

    /**
     * Valida un objeto (dto)
     * @param objeto dto a validar
     * @param <T> tipo del dto a validar
     */
    public <T> void validate(T objeto) {
        Set<ConstraintViolation<T>> errores = validator.validate(objeto);
        mapMessageErrores(errores);
    }

    /**
     * Se valida un dto, con diferentes marcas de grupos de validación
     * Los errores se guradan y se lanza una excepción, forma de orden de llegada
     * @param objeto
     * @param grupos
     * @param <T>
     */
    public <T> void validateWithGroups(T objeto, Class<?>... grupos) {
        Set<ConstraintViolation<T>> errores = validator.validate(objeto, grupos);
        mapMessageErrores(errores);
    }

    /**
     * Todos los errores que están en cola, los mapea en una lista
     * y los lanza como tipo de personalizado 'ExecptionValidation'
     * @param errores cola errores a mapear
     * @param <T> tipo del dto a validar
     */
    private static <T> void mapMessageErrores(Set<ConstraintViolation<T>> errores) {
        if (!errores.isEmpty()) {
            List<ObjectErrorValidation> listErrores = errores.stream()
                    .map(error ->
                            new ObjectErrorValidation(error.getPropertyPath().toString(), error.getMessage())).toList();

            throw new EValidation(listErrores);
        }
    }
}
