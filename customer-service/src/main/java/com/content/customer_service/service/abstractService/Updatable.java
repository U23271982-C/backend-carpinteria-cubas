package com.content.customer_service.service.abstractService;

/**
 * Interfaz para actualizar una entidad
 * @param <DRQ> DTO de Request (entrada)
 * @param <DRE> DTO de Response (salida)
 */
public interface Updatable<DRQ, DRE> {
    /**
     * Actualiza una entidad existente
     * @param id ID de la entidad a actualizar
     * @param dto DTO con los nuevos datos
     * @return DTO de respuesta con la entidad actualizada
     */
    DRE update(int id, DRQ dto);
}

