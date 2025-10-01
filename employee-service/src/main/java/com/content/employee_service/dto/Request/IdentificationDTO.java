package com.content.employee_service.dto.Request;

import com.content.employee_service.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * Data Transfer Object para la entidad Identification.
 * Este DTO incluye lista de las entidades relaciones.
 */

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
