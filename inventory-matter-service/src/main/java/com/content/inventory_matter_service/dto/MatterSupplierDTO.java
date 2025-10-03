package com.content.inventory_matter_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class MatterSupplierDTO {

    private Integer id;

    private Integer Supplier_id;

    private Integer Matter_id;

    private double unit_price;

    private Integer time_delivery;

    private String observation;

    private LocalDate log_date;

    private LocalDate update_date;

    private Integer State_id;
}
