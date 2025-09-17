package com.content.trabajador_servicio.mapper;

public interface ConvertModel<M, D> {
    M toModel(D dto);
}
