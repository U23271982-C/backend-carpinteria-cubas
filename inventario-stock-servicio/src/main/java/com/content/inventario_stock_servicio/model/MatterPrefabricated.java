package com.content.inventario_stock_servicio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class MatterPrefabricated {
    private int id;
    private int id_Matter;
    private int currently_stock;
    private int min_stock;
}
