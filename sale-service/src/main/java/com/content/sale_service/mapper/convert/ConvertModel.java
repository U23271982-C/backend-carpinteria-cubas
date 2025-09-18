package com.content.sale_service.mapper.convert;

/**
 *
 * @param <M> Model
 * @param <D> DTO
 */
public interface ConvertModel<M, D> {
    M toModel(D dto);
}
