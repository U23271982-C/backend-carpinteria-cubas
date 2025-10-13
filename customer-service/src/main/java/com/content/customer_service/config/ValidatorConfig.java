package com.content.customer_service.config;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración para el validador de Jakarta Validation
 * Necesario cuando spring-boot-starter-validation no está disponible o no se autoconfigura
 */
@Configuration
public class ValidatorConfig {

    /**
     * Crea el bean Validator manualmente
     * Este bean es requerido por UtilityValidator
     */
    @Bean
    public Validator validator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        return factory.getValidator();
    }
}

