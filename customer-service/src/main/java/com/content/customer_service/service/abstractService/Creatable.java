package com.content.customer_service.service.abstractService;

/**
 * Interfaz para crear una entidad
 * @param <DRQ> DTO de Request (entrada)
 * @param <DRE> DTO de Response (salida)
 */
public interface Creatable<DRQ, DRE> {
    /**
     * Crea una nueva entidad
     * @param dto DTO de entrada con los datos para crear
     * @return DTO de respuesta con la entidad creada
     */
    DRE create(DRQ dto);
}

