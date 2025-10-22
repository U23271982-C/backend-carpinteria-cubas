package com.content.customer_service.service.abstractService;

import jakarta.transaction.Transactional;

/**
 * Interfaz para crear una entidad
 * @param <DRQ> DTO de Request (entrada)
 * @param <DRE> DTO de Response (salida)
 */
@Transactional
public interface Creatable<DRQ, DRE> {
    DRE create(DRQ dto);
}

