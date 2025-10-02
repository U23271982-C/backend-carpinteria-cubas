package com.content.inventario_stock_servicio.service.abstractService;

public interface Creatable <DRQ, DRE>{
    DRE create(DRQ dto);
}
