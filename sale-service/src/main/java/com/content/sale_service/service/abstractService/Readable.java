package com.content.sale_service.service.abstractService;

/**
 *
 * @param <DRE> response DTO
 */
public interface Readable <DRE>{
    DRE readById(Long id);
}
