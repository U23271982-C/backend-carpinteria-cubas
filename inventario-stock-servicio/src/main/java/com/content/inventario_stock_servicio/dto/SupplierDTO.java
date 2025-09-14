package com.content.inventario_stock_servicio.dto;

import com.content.inventario_stock_servicio.model.MatterSupplier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class SupplierDTO {

    private Integer id;

    private String name_Supplier;

    private String ruc;

    private String cell;

    private List<MatterSupplier> MatterSupplier;

    private Integer State_id;
}
