package com.content.inventory_matter_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class MovementStockCustomizedDTO {

    private Integer id;

    private Integer Matter_id;

    private Integer MovementType_id;

    private Integer quantity;

    private Integer stock_before;

    private Integer stock_new;

    private String user_employee;

    private Integer State_id;
}
