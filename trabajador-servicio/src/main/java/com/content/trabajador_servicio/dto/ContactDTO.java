package com.content.trabajador_servicio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
