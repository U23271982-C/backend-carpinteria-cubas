package com.content.employee_service.dto.Request;

import com.content.employee_service.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Data Transfer Object para la entidad.
 * Este DTO incluye lista de las entidades relaciones.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor

public class StateDTO {

    private Integer id;

    private String name_State;

    private List<Contact> Contact;

    private List<Contract> Contract;

    private List<ContractType> Contracttype;

    private List<Distric> Distric;

    private List<Employee> Employee;

    private List<Identification> Identification;

    private List<IdentificationType> Identificationtype;

    private List<PersonType> Persontype;

    private List<Post> Post;
}
