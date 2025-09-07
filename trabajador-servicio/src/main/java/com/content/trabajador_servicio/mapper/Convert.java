package com.content.trabajador_servicio.mapper;

public interface Convert <T, D>{
    D convertDTO(T modelo);
    T convertModel(D dto);
}
