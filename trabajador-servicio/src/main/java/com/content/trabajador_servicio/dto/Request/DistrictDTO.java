package com.content.trabajador_servicio.dto.Request;

import com.content.trabajador_servicio.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Data Transfer Object para la entidad District.
 * Este DTO incluye lista de las entidades relaciones.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistrictDTO {

    private Integer id;

    private String name_Distric;

    private List<Employee> employees;

    private Integer id_State;
}
