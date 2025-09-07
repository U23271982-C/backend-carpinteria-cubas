package com.content.trabajador_servicio.dto;

import java.time.LocalDateTime;

public class EmployeeDTO {
    private int id;
    private String firtsname_Employee;
    private String lastname_Employee;
    private LocalDateTime date_Birth;
    private LocalDateTime date_Admission;
    private PositionDTO id_Position;
    private IdentificationDTO id_Identification;
    private ContractDTO id_Contract;
    private StateDTO id_State;
}
