package com.content.employee_service.dto.Request;

import java.time.LocalDateTime;
import java.util.List;
import com.content.employee_service.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object para la entidad Contract.
 * Este DTO incluye lista de las entidades relaciones.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContractDTO {

    private Integer id;

    private String name_Contract;

    private LocalDateTime start_Date;

    private LocalDateTime finish_Date;

    private Integer contract_Type;

    private double salary;

    private List<Employee> employees;

    private Integer id_State;
}
