package com.content.inventario_stock_servicio.service.abstractService;

import java.util.List;

public interface Listable <D>{
    List<D> list(Long id);
}
