package com.content.trabajador_servicio.service.abstractService;

import java.util.List;

/**
 *
 * @param <DRE> DTO response de trabajador_servicio
 */

public interface Listable<DRE>{
    List<DRE> allList();
}
