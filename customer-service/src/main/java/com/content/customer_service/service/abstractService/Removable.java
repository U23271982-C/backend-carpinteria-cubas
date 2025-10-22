package com.content.customer_service.service.abstractService;

import jakarta.transaction.Transactional;

import java.util.UUID;

/**
 * Interfaz para eliminar una entidad
 */
@Transactional
public interface Removable {
    void remove(UUID uuid);
}

