package com.content.employee_service.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object para la entidad Contact.
 * Este DTO no incluye lista de las entidades relaciones.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactDTO {

    private Integer id;

    private Integer id_Employee;

    private String telephone;

    private String email;

    private Integer id_State;
}
