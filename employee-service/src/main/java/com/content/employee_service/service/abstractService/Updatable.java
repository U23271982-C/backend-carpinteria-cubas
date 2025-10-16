package com.content.employee_service.service.abstractService;

import jakarta.transaction.Transactional;

import java.util.UUID;

/**
 *
 * @param <DRQ> request DTO
 * @param <DRE> response DTO
 */
@Transactional
public interface Updatable<DRQ,DRE> {
    DRE updateByUUID(UUID uuid, DRQ dto);
}
