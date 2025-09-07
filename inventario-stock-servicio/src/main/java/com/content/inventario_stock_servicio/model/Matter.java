package com.content.inventario_stock_servicio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class Matter {
    private int id;
    private String name_Matter;
    private String path_image;
    private int id_Matter_Type;
    private int id_State_Matter;
    private String description;
    private double cost;
    private double unit_measure;
}
