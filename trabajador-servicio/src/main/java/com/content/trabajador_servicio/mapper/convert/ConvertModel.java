package com.content.trabajador_servicio.mapper.convert;

public interface ConvertModel<M, D> {
    M toModel(D dto);
}
