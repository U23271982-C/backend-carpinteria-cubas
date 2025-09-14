package com.content.trabajador_servicio.dto;

import com.content.trabajador_servicio.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class StateDTO {

    private Integer id;

    private String name_State;

    private List<Address> Address;

    private List<AddressType> Addresstype;

    private List<Contact> Contact;

    private List<Contract> Contract;

    private List<ContractType> Contracttype;

    private List<Department> Department;

    private List<Distric> Distric;

    private List<Employee> Employee;

    private List<Identification> Identification;

    private List<IdentificationType> Identificationtype;

    private List<PersonType> Persontype;

    private List<Position> Position;

    private List<Province> Province;

}
