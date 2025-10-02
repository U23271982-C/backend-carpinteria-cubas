package com.content.inventario_stock_servicio.dto;

import com.content.inventario_stock_servicio.model.Matter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class MatterStateDTO {

    private Integer id;

    private String matterState_name;

    private Integer State_id;

    private List<Matter> Matters;
}
