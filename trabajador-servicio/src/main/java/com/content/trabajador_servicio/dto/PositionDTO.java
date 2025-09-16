package com.content.trabajador_servicio.dto;

import com.content.trabajador_servicio.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PositionDTO {

    private Integer id;

    private String name_Position;

    private String description;

    private List<Employee> employees;

    private Integer id_State;
}
