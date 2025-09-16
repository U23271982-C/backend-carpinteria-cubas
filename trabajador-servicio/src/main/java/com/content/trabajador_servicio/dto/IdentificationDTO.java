package com.content.trabajador_servicio.dto;

import com.content.trabajador_servicio.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdentificationDTO {

    private Integer id;

    private String name_Identification;

    private Integer id_Identification_Type;

    private List<Employee> employees;

    private Integer id_State;
}
