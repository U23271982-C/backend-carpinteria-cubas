package com.content.customer_service.service.abstractService;

import java.util.List;

/**
 * Interfaz abstracta para servicios - USA SOLO UUIDs
 * @param <REQUEST> DTO de request
 * @param <RESPONSE> DTO de response
 */
public interface ServiceAbs<REQUEST, RESPONSE> {

    /**
     * Crear una nueva entidad
     */
    RESPONSE create(REQUEST dto);

    /**
     * Obtener entidad por UUID (identificador público)
     */
    RESPONSE getByUuid(String uuid);

    /**
     * Obtener todas las entidades activas
     */
    List<RESPONSE> getAll();

    /**
     * Actualizar entidad por UUID
     */
    RESPONSE update(String uuid, REQUEST dto);

    /**
     * Eliminar entidad por UUID (soft delete)
     */
    void delete(String uuid);
}
