package com.content.inventario_stock_servicio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class MatterType {
    private int id;
    private String name_MatterType;
}
