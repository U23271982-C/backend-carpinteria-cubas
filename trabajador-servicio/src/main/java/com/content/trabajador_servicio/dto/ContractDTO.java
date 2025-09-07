package com.content.trabajador_servicio.dto;

import java.time.LocalDateTime;

public class ContractDTO {
    private int id;
    private String name_Contract;
    private LocalDateTime start_Date;
    private LocalDateTime finish_Date;
    private String contract_Type;
    private double salary;
    private StateDTO id_State;
}
