package com.content.authentication_service.service.abstractservice;

/**
 * Método que permite registrar y persistir una entidad en la base de datos.
 * @param <DRQ> Request DTO
 * @param <DRE> Response DTO
 */
public interface Creatable <DRQ, DRE> {
    DRE create(DRQ dto);
}
