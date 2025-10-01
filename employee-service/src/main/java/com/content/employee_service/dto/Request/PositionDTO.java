package com.content.employee_service.dto.Request;

import com.content.employee_service.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * Data Transfer Object para la entidad Position.
 * Este DTO incluye lista de las entidades relaciones.
 */

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
