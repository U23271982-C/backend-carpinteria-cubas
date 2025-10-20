package com.content.authentication_service.service.abstractservice;

import java.util.UUID;

/**
 *
 * @param <DRQ> Request DTO
 * @param <DRE> Response DTO
 */
public interface Updatable<DRQ,DRE> {
    DRE update(UUID uuid, DRQ dto);
}
