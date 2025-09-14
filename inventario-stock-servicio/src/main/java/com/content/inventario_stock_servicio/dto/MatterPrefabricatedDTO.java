package com.content.inventario_stock_servicio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class MatterPrefabricatedDTO {

    private Integer id;

    private Integer Matter_id;

    private Integer currently_stock;

    private Integer min_stock;

    private Integer State_id;
}
