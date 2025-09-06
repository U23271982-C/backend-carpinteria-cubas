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
public class Employee {
    private int id;
    private String firtsname_Employee;
    private String lastname_Employee;
    private LocalDateTime date_Birth;
    private LocalDateTime date_Admission;
    private int id_Position;
    private int id_Identification;
    private int id_Contract;
}
