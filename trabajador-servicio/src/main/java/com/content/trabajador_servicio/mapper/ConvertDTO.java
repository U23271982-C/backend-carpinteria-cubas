package com.content.trabajador_servicio.mapper;

public interface ConvertDTO<M, D> {
    D toDTO(M model);
}
