package com.content.inventario_stock_servicio.mapper.convert;

public interface ConvertDTO <M,D>{
    D toDTO(M model);
}
