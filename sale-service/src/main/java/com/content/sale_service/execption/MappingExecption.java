package com.content.sale_service.execption;

import lombok.Getter;

/**
 *Mapeo de excepciones a tipos de error para mostrar mensajes específicos
 *Por cada tipo de excepción creada en /excepcion, se debe agregar aquí
 */
@Getter
public enum MappingExecption {
    VALIDACION_EXECPTION(ExecptionValidation.class, "Error de validación");


    Class<?> errorType;
    String title;

    MappingExecption(Class<ExecptionValidation> execptionValidationClass, String title) {}

}
