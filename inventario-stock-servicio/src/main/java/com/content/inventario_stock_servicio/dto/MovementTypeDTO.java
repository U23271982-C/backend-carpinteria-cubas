package com.content.inventario_stock_servicio.dto;

import com.content.inventario_stock_servicio.model.MovementStockCustomized;
import com.content.inventario_stock_servicio.model.MovementStockPrefabricated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class MovementTypeDTO {

    private Integer id;

    private String movement_type_name;

    private List<MovementStockPrefabricated> MovementStockPrefabricated;

    private List<MovementStockCustomized> MovementStockCustomized;

    private Integer State_id;
}
