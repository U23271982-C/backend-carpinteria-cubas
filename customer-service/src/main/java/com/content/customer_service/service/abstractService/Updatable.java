package com.content.customer_service.service.abstractService;

import jakarta.transaction.Transactional;

import java.util.UUID;

/**
 * Interfaz para actualizar una entidad
 * @param <DRQ> DTO de Request (entrada)
 * @param <DRE> DTO de Response (salida)
 */
@Transactional
public interface Updatable<DRQ, DRE> {
    DRE updateByUUID(UUID uuid, DRQ dto);
}

