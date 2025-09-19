package com.content.inventario_stock_servicio.mapper.convert;

public interface ConvertModel <M,D>{
    M toModel(D dto);
}
