package com.content.inventario_stock_servicio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class MatterSupplier {
    private int id;
    private int id_Supplier;
    private int id_Matter;
    private double unit_price;
    private int time_delivery;
    private String observaciones;
    private LocalDate log_date;
    private LocalDate update_date;
}
