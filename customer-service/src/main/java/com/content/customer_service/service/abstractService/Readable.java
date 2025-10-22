package com.content.customer_service.service.abstractService;

import jakarta.transaction.Transactional;

import java.util.UUID;

/**
 * Interfaz para leer una entidad por ID
 * @param <DRE> DTO de Response (salida)
 */
@Transactional
public interface Readable<DRE> {
    DRE readByUUID(UUID uuid);
}

