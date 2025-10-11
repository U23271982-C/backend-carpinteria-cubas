package com.content.employee_service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

/**
 *Mapeo de excepciones a tipos de error para mostrar mensajes específicos
 *Por cada tipo de excepción creada en /excepcion, se debe agregar aquí
 */
@Getter
public enum MappingExecption {

    VALIDACION_EXECPTION(EValidation.class, "Error de validación", HttpStatus.BAD_REQUEST),
    GENERIC(Exception.class, "Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);

    private final Class<? extends Exception> errorType;
    private final String title;
    private final HttpStatus httpStatus;


    MappingExecption(Class<? extends Exception> execptionValidationClass, String title, HttpStatus httpStatus) {
        this.errorType = execptionValidationClass;
        this.title = title;
        this.httpStatus = httpStatus;
    }

    /**
     *
     * @param e
     * @return
     */
    public static MappingExecption fromException(Exception e) {
        return Arrays.stream(values())
                .filter(m -> m.errorType.isAssignableFrom(e.getClass()))
                .findFirst()
                .orElse(GENERIC);
    }
}
