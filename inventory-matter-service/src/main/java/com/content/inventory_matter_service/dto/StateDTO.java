package com.content.inventory_matter_service.dto;

import com.content.inventory_matter_service.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class StateDTO {

    private int id;

    private String name_State;

    private List<Matter> Matters;

    private List<MatterCustomized> Matterscustomized;

    private List<MatterPrefabricated> Mattersprefabricated;

    private List<MatterState> Matterstate;

    private List<MatterSupplier> Matterssupplier;

    private List<MatterType> Matterstype;

    private List<MovementStockCustomized> Movementstockcustomized;

    private List<MovementStockPrefabricated> Movementstockprefabricated;

    private List<MovementType> Movementtype;

    private List<Supplier> Supplier;
}
