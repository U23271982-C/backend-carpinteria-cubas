package com.content.sale_service.mapper.convert;

public interface ConvertDTO<M, D> {
    D toDTO(M modelo);
}
