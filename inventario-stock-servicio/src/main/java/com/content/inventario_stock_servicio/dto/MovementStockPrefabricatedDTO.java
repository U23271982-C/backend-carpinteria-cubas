package com.content.inventario_stock_servicio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class MovementStockPrefabricatedDTO {

    private Integer id;

    private Integer Matter_id;

    private Integer MovementType_id;

    private Integer quantity;

    private Integer stock_before;

    private Integer stock_new;

    private String user_employee;

    private Integer State_id;
}
