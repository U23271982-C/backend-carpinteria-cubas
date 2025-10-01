package com.content.employee_service.dto.Request;

import java.time.LocalDateTime;
import java.util.List;

import com.content.employee_service.model.Contact;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object para la entidad Employee.
 * Este DTO incluye lista de las entidades relaciones.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeRequestDTO {

    private Integer id;

    private String firtsname_Employee;

    private String lastname_Employee;

    private LocalDateTime date_Birth;

    private LocalDateTime date_Admission;

    private String address_Employee;

    private Integer id_Distric;

    private Integer id_Position;

    private Integer id_Identification;

    private Integer id_Contract;

    private List<Contact> contacts;

    private Integer id_State;
}
