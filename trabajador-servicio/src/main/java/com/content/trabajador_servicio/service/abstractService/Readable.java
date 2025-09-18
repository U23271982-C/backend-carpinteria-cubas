package com.content.trabajador_servicio.service.abstractService;

import java.util.List;

public interface Readable<D>{
    List<D> read(Long id);
}
