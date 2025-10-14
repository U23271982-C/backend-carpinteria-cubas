package com.content.employee_service.service.abstractService;

import java.util.UUID;

/**
 *
 * @param <DRQ> request DTO
 * @param <DRE> response DTO
 */
public interface Updatable<DRQ,DRE> {
    DRE updateByUUID(UUID uuid, DRQ dto);
}
