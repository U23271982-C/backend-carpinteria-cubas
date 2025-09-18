package com.content.sale_service.service.abstractService;

import java.util.List;

/**
 *
 * @param <DRE> DTO response de venta
 */
public interface Listable<DRE> {
    List<DRE> allList();
}
