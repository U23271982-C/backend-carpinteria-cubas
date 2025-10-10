package com.content.sale_service.service.abstractService;

/**
 * Método que permite registrar y persistir una entidad en la base de datos.
 * @param <DRQ> request DTO
 * @param <DRE> response DTO
 */
public interface Creatable<DRQ, DRE> {
    DRE create(DRQ dto);
}
