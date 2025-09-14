package com.content.trabajador_servicio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    private Integer id;

    private Integer id_Employee;

    private Integer id_Address_Type;

    private String name_Via;

    private String number;

    private String reference;

    private Integer id_Distric;

    private Integer id_State;
}
