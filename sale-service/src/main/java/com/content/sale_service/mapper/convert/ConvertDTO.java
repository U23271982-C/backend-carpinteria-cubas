package com.content.sale_service.mapper.convert;

/**
 *
 * @param <M> Model
 * @param <D> DTO
 */
public interface ConvertDTO<M, D> {
    D toDTO(M modelo);
}
