package com.content.inventario_stock_servicio.service.abstractService;

public interface Updatable <DRQ,DRE> {
    DRE update(int id, DRQ dto);
}
