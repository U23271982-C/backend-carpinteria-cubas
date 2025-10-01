package com.content.employee_service.service.abstractService;

import java.util.List;

/**
 *
 * @param <DRE> DTO response de trabajador_servicio
 */

public interface Listable<DRE>{
    List<DRE> allList();
}
