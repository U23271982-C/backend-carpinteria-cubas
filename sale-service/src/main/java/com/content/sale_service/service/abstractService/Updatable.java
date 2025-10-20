package com.content.sale_service.service.abstractService;

/**
 *
 * @param <DRQ> request DTO
 * @param <DRE> response DTO
 */
public interface Updatable<DRQ,DRE> {
    DRE update(int id, DRQ dto);
}
