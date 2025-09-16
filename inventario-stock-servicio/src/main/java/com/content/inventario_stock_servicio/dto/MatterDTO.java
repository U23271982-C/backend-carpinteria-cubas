package com.content.inventario_stock_servicio.dto;

import com.content.inventario_stock_servicio.model.MatterSupplier;
import com.content.inventario_stock_servicio.model.MovementStockCustomized;
import com.content.inventario_stock_servicio.model.MovementStockPrefabricated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class MatterDTO {

    private Integer id;

    private String name_Matter;

    private String path_image;

    private Integer MatterType_id;

    private Integer MatterState_id;

    private String description;

    private double cost;

    private double unit_measure;

    private Integer State_id;

    private Integer MatterCustomized_id;

    private Integer MatterPrefabricated_id;

    private List<MovementStockPrefabricated> MovementStockPrefabricated;

    private List<MovementStockCustomized> MovementStockCustomized;

    private List<MatterSupplier> MatterSuppliers;
}
