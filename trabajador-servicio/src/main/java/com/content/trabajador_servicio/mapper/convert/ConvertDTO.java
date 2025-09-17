package com.content.trabajador_servicio.mapper.convert;

public interface ConvertDTO<M, D> {
    D toDTO(M model);
}
