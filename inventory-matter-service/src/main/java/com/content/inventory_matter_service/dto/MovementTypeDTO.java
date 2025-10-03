package com.content.inventory_matter_service.dto;

import com.content.inventory_matter_service.model.MovementStockCustomized;
import com.content.inventory_matter_service.model.MovementStockPrefabricated;
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
