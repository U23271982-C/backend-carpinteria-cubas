package com.content.customer_service.mapper.convert;

/**
 * Generic interface for converting a model to a DTO.
 *
 * @param <M> Modelo
 * @param <D> DTO
 */

public interface ConvertDTO<M, D> {
    D toDTO(M model);
}
