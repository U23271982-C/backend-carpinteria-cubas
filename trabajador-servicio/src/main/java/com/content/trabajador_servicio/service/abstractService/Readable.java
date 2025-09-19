package com.content.trabajador_servicio.service.abstractService;

public interface Readable<DRE>{
    DRE readById(Long id);
}
