package com.content.customer_service.service.abstractService;

/**
 * Interfaz para leer una entidad por ID
 * @param <DRE> DTO de Response (salida)
 */
public interface Readable<DRE> {
    /**
     * Busca una entidad por su ID
     * @param id ID de la entidad a buscar
     * @return DTO de respuesta con la entidad encontrada
     */
    DRE readById(Long id);
}

