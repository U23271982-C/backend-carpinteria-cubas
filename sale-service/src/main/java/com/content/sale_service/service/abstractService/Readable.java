package com.content.sale_service.service.abstractService;

/**
 *
 * @param <DRE> Response DTO
 */
public interface Readable <DRE>{
    DRE readById(Long id);
}
