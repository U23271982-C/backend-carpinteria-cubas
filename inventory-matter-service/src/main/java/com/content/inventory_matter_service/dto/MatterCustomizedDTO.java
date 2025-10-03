package com.content.inventory_matter_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class MatterCustomizedDTO {

    private Integer id;

    private Integer Matter_id;

    private Integer currently_stock;

    private Integer min_stock;

    private Integer State_id;
}
