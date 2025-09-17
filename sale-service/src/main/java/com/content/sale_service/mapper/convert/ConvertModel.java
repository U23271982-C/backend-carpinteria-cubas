package com.content.sale_service.mapper.convert;

public interface ConvertModel<M, D> {
    M toModel(D dto);
}
