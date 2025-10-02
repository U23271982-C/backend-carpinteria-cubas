package com.content.inventario_stock_servicio.dto;

import com.content.inventario_stock_servicio.model.Matter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class MatterTypeDTO {

    private Integer id;

    private String mattertype_name;

    private List<Matter> Matter;

    private Integer State_id;

}
