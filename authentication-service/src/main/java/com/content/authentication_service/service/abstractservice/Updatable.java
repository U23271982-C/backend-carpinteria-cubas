package com.content.authentication_service.service.abstractservice;

/**
 *
 * @param <DRQ> Request DTO
 * @param <DRE> Response DTO
 */
public interface Updatable<DRQ,DRE> {
    DRE update(int id, DRQ dto);
}
