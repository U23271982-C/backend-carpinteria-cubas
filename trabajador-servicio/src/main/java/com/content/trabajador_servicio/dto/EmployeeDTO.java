package com.content.trabajador_servicio.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.content.trabajador_servicio.model.Address;
import com.content.trabajador_servicio.model.Contact;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private Integer id;

    private String firtsname_Employee;

    private String lastname_Employee;

    private LocalDateTime date_Birth;

    private LocalDateTime date_Admission;

    private Integer id_Position;

    private Integer id_Identification;

    private Integer id_Contract;

    private List<Contact> contacts;

    private List<Address> addresses;

    private Integer id_State;
}
