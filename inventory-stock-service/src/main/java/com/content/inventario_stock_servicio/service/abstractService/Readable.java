package com.content.inventario_stock_servicio.service.abstractService;

public interface Readable <DRE> {
    DRE readById(Long id);
}
