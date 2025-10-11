package com.content.authentication_service.service.abstractservice;

import java.util.List;

/**
 *
 * @param <DRE> DTO response de trabajador_servicio
 */
public interface Listable<DRE>{
    List<DRE> allList();
}
