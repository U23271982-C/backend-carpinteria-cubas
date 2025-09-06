package com.content.trabajador_servicio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class Contract {
    private int id;
    private String name_Contract;
    private LocalDateTime start_Date;
    private LocalDateTime finish_Date;
    private String contract_Type;
    private double salary;
}
