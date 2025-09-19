package com.content.trabajador_servicio.service.abstractService;

/**
 *
 * @param <DRQ> Request DTO
 * @param <DRE> Response DTO
 */
public interface Updatable<DRQ,DRE> {
    DRE update(int id, DRQ dto);
}
